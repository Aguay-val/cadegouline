import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup,Validators} from "@angular/forms";
import {AnimateurService} from "../services/animateur.service";
import {Track} from "../models/Track.model";
import {ThemePalette} from '@angular/material/core';

@Component({
    selector: 'app-animateur',
    templateUrl: './animateur.component.html',
    styleUrls: ['./animateur.component.css']
})
export class AnimateurComponent implements OnInit {

    title: string;
    track: Track;
    file: File;
    fileArray: Promise<ArrayBuffer>;

    trackForm: FormGroup;

    constructor(public animateurService: AnimateurService) { }

    ngOnInit(): void {
        this.trackForm = new FormGroup({
            title: new FormControl(null, [
                Validators.required
            ]),
            artist: new FormControl(null, [
                Validators.required
            ]),
            album: new FormControl(),
            old_or_new: new FormControl(null, [
                Validators.required
            ]),
            file: new FormControl(null, [
                Validators.required
            ])
        })
    }

    onFileSelected(event) {
        this.file = event.target.files[0]
        this.fileArray = this.file.arrayBuffer()
    }

    onSubmit(): void {
        this.track = new Track(
            this.trackForm.value.title,
            this.trackForm.value.artist,
            this.trackForm.value.album,
            this.trackForm.value.old_or_new,
            this.file.name
        );
        this.title = "";
        const jsonTrack = JSON.stringify(this.track);
        this.animateurService.saveTrack(this.file, jsonTrack);

    }

}
