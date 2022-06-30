import axios from 'axios';
import {webConfig} from "@/web-config";

export default axios.create({
    baseURL: webConfig.baseUrl,
    headers: {
        'Content-Type': 'application/json'
    }
});

