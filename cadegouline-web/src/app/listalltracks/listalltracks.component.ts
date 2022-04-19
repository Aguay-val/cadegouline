import { Component, OnInit  } from '@angular/core';
import {ListAllTracksService} from "../services/list-all-tracks.service";
import {Track} from "../models/Track.model";

@Component({
  selector: 'app-listalltracks',
  templateUrl: './listalltracks.component.html',
  styleUrls: ['./listalltracks.component.css']
})
export class ListalltracksComponent implements OnInit {

  allTracks : Track[];

  displayedColumns = ["titre", "album", "artist", "tag", "fichier"];

  constructor(private listAllService : ListAllTracksService) { }

  ngOnInit(): void {
    this.listAllService.getAll().subscribe( data => {
      this.allTracks = data;
    });

  }

}
