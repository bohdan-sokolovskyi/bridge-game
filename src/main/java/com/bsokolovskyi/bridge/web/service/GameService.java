package com.bsokolovskyi.bridge.web.service;

import com.bsokolovskyi.bridge.core.enums.Card;
import com.bsokolovskyi.bridge.core.enums.GameStatus;
import com.bsokolovskyi.bridge.web.db.entity.Game;
import com.bsokolovskyi.bridge.web.db.entity.Player;
import com.bsokolovskyi.bridge.web.db.entity.User;
import com.bsokolovskyi.bridge.web.db.repository.GameRepository;
import com.bsokolovskyi.bridge.web.db.repository.UserRepository;
import com.bsokolovskyi.bridge.web.dto.GameDTO;
import com.bsokolovskyi.bridge.web.dto.GameProgressDTO;
import com.bsokolovskyi.bridge.web.exception.GameAlreadyStartedException;
import com.bsokolovskyi.bridge.web.exception.GameExistException;
import com.bsokolovskyi.bridge.web.exception.GameNotExistException;
import com.bsokolovskyi.bridge.web.exception.UserNotExistException;
import com.bsokolovskyi.bridge.web.request.ConnectGameRequest;
import com.bsokolovskyi.bridge.web.request.CreateGameRequest;
import com.bsokolovskyi.bridge.web.request.GameDataRequest;
import com.bsokolovskyi.bridge.web.response.CreateGameResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class GameService {

    private final GameRepository gameRepository;
    private final UserRepository userRepository;
    private final PlayerService playerService;

    public GameService(@Autowired GameRepository gameRepository,
                       @Autowired UserRepository userRepository,
                       @Autowired PlayerService playerService) {
        this.gameRepository = gameRepository;
        this.userRepository = userRepository;
        this.playerService = playerService;
    }

    public CreateGameResponse createGame(CreateGameRequest request) throws UserNotExistException, GameExistException {
        User user = userRepository.findByEmail(request.getEmail());

        if(user == null) {
            throw new UserNotExistException(request.getEmail());
        }

        if(gameRepository.findByGameId(generateGameId()) != null) {
            throw new GameExistException(request.getGameName());
        }

        Game game = new Game();
        game.setGameId(generateGameId());
        game.setGameName(request.getGameName());
        game.setGameStatus(GameStatus.IN_PROGRESS); // NEW BEFORE

        // init deck
        List<Card> deck = Card.getNewDeck();
        Collections.shuffle(deck);

        game.setDeck(new ArrayDeque<>(deck));
        game.setUsedDeck(new ArrayDeque<>());


        Player player = playerService.createPlayer(user, getCardsForPlayer(Card.COUNT_OF_STARTED_CARDS_FOR_FIRST_PLAYER, game));

        game.setFirstPlayer(player);
        game.getUsedDeck().add(getCardsForPlayer(1, game).iterator().next());

        gameRepository.save(game);

        CreateGameResponse response = new CreateGameResponse();
        response.setGameId(game.getGameId());
        response.setCards(new ArrayList<>(player.getCards()));

        return response;
    }

    public void connectToGame(ConnectGameRequest request) throws UserNotExistException, GameNotExistException, GameAlreadyStartedException {
        User user = userRepository.findByEmail(request.getEmail());

        if(user == null) {
            throw new UserNotExistException(request.getEmail());
        }

        Game game = gameRepository.findByGameId(request.getGameId());

        if(game == null) {
            throw new GameNotExistException(request.getGameId());
        }

        if(game.getGameStatus().equals(GameStatus.IN_PROGRESS)) {
            throw new GameAlreadyStartedException(request.getGameId());
        }

        game.getPlayers().add(playerService.createPlayer(user, getCardsForPlayer(Card.COUNT_OF_STARTED_CARDS, game)));
        game.setGameStatus(GameStatus.IN_PROGRESS);

        gameRepository.save(game);
    }

    public GameProgressDTO updateGameState(GameDataRequest request) throws GameNotExistException {
        GameProgressDTO response = new GameProgressDTO();
        response.setGameId(request.getGameId());

        Game game = gameRepository.findByGameId(request.getGameId());

        if(game == null) {
            throw new GameNotExistException(request.getGameId());
        }

        response.setCard(getCardsForPlayer(1, game).iterator().next());

        return response;
    }

    public Map<String, GameDTO> getAllGames() {
        return gameRepository.findAll().stream().collect(Collectors.toMap(Game::getGameId, GameDTO::new));
    }

    private String generateGameId() {
        return UUID.randomUUID().toString().toLowerCase();
    }

    private Set<Card> getCardsForPlayer(int n, Game game) {
        //TODO: check if deck is empty

        if(n <= 0) {
            throw new IllegalArgumentException("cards < 0");
        }

        Set<Card> cards = new HashSet<>();

        for(int i = 1; i <= n; i++){
            cards.add(game.getDeck().pollFirst());
        }

        return cards;
    }
}
