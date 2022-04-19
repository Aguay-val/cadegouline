import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Track} from "../models/Track.model";
import {Observable} from "rxjs";

@Injectable()
export class ListAllTracksService {

    constructor(private httpClient: HttpClient) {

    }

    getAll() : Observable<Track[]> {
        let environment = window.location.hostname
        environment = "://" + environment + ":3333";
        const url = "http" + environment + "/api/v1/track/getAll"
        console.log(url);
        return this.httpClient.get<Track[]>(url);
    }
}