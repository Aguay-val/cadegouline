import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import { ToastrService } from 'ngx-toastr';

const httpOptions = {
    headers: new HttpHeaders({
        Authorization: 'my-auth-token'
    })
};


@Injectable()
export class AnimateurService {

    constructor(private httpClient: HttpClient, private toastr: ToastrService) {

    }

    saveTrack(file: File, track: string) {
        const formData = new FormData();
        formData.append("fileTrack", file);
        formData.append("track", track);
        this.httpClient.post<string>('http://localhost:3333/api/v1/track/insert', formData, httpOptions).subscribe(
            () => {

            },
            (error) => {
                console.log(error)
                if (error.status == 200) {
                    this.toastr.success("Le fichier a bien été enregistré ! ", "Succès !");
                } else {
                    this.toastr.error("Une erreur est survenue", "Erreur");
                }
            }
        );
    }

}