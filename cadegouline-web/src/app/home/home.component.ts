import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

    @ViewChild('playDiv', { static: false }) public _playRef: ElementRef;
    @ViewChild('pauseDiv', { static: false }) public _pauseRef: ElementRef;
    @ViewChild('audioElement', { static: false }) public _audioRef: ElementRef;

    private realAudio: HTMLAudioElement;
    private playDiv: HTMLElement;
    private pauseDiv: HTMLElement;

    showVolume: boolean;
    volume: number;

    constructor() { }

    ngOnInit(): void {
        this.showVolume = false;
        this.volume = 100;
    }

    public ngAfterViewInit() {
        this.realAudio = this._audioRef.nativeElement;
        /*
        this.realAudio.onloadedmetadata = (event) => {
            console.log(event);
        };
        */
        this.playDiv = this._playRef.nativeElement;
        this.pauseDiv = this._pauseRef.nativeElement;
    }


    play() {
        this.realAudio.play();
        this.playDiv.style.display = "none"
        this.pauseDiv.style.display = "block"
    }

    pause() {
        this.realAudio.pause();
        this.pauseDiv.style.display = "none"
        this.playDiv.style.display = "block"
    }

    soundControl() {
        this.showVolume = !this.showVolume;
    }

    setVolume(event) {
        this.realAudio.volume = (event.value / 100);
    }

}
