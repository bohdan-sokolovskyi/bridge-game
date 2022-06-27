package com.bsokolovskyi.bridge.web.service;

import com.bsokolovskyi.bridge.core.GameStatus;
import com.bsokolovskyi.bridge.web.db.entity.Game;
import com.bsokolovskyi.bridge.web.db.entity.User;
import com.bsokolovskyi.bridge.web.db.repository.GameRepository;
import com.bsokolovskyi.bridge.web.db.repository.UserRepository;
import com.bsokolovskyi.bridge.web.dto.GameDTO;
import com.bsokolovskyi.bridge.web.exception.GameAlreadyStartedException;
import com.bsokolovskyi.bridge.web.exception.GameExistException;
import com.bsokolovskyi.bridge.web.exception.GameNotExistException;
import com.bsokolovskyi.bridge.web.exception.UserNotExistException;
import com.bsokolovskyi.bridge.web.request.ConnectGameRequest;
import com.bsokolovskyi.bridge.web.request.CreateGameRequest;
import com.bsokolovskyi.bridge.web.request.GameDataRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class GameService {

    private final GameRepository gameRepository;
    private final UserRepository userRepository;

    public GameService(@Autowired GameRepository gameRepository,
                       @Autowired UserRepository userRepository) {
        this.gameRepository = gameRepository;
        this.userRepository = userRepository;
    }

    public String createGame(CreateGameRequest request) throws UserNotExistException, GameExistException {
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
        game.setFirstPlayer(user);
        game.setGameStatus(GameStatus.NEW);

        gameRepository.save(game);
        return game.getGameId();
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

        game.getPlayers().add(user);
        game.setGameStatus(GameStatus.IN_PROGRESS);
        //TODO: run game process
        gameRepository.save(game);
    }

    public void updateGameData(GameDataRequest request) {
        //TODO: implement me
    }

    public Map<String, GameDTO> getAllGames() {
        return gameRepository.findAll().stream().collect(Collectors.toMap(Game::getGameId, GameDTO::new));
    }

    private String generateGameId() {
        return UUID.randomUUID().toString().toLowerCase();
    }
}
