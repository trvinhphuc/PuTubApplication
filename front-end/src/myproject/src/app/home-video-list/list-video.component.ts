import { Component, Input, OnInit } from '@angular/core';
import 'rxjs/add/operator/switchMap';
import { Observable } from 'rxjs/Observable';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { VideoService } from '../services/video_service.service';
import { VideoComponent } from './../video/video.component';
@Component({
    selector: 'list-video',
    
    styleUrls: ['./list-video.component.css'],
    templateUrl:'./list-video.component.html',
    
    providers: [VideoService],
})
export class ListVideoComponent implements OnInit {

    videos: any;

    constructor(
        private VideoService: VideoService,
        private router : Router
    ) { }
    select(){
        this.router.navigate(['channel',this.user.id]);
    }
    getVideos(){
        //console.log(this.user);
        this.VideoService.getVideoList(this.user.id)
        
        .subscribe(data =>{
            this.videos = data.slice(0,4)
        })
    }
    ngOnInit(): void {
        this.getVideos();
        //console.log(this.videos);
    }
    @Input() user: any;

}