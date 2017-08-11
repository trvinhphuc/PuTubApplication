import { Component, Input } from '@angular/core';
import 'rxjs/add/operator/switchMap';
import { Observable } from 'rxjs/Observable';
import { Router, ActivatedRoute, Params } from '@angular/router';

import { VideoService } from '../services/video_service.service';




@Component({
    selector: 'onevideo',
    styleUrls: ['./video.component.css'],
    templateUrl: './video.component.html'

})
export class VideoComponent {
    videos: Observable<any>;
    private selectedId: number;
    @Input() video: any;
    constructor(
        private videoService: VideoService,
        //private service: Video_Service,
        private route: ActivatedRoute,
        private router: Router
    ) { }
    ngOnInit() {
        //console.log(this.video);
        this.videos = this.route.params
            .switchMap((params: Params) => {
                this.selectedId = +params['id'];
                return this.videoService.getVid(+params['id']);
                //return this.video;
            });
    }
    isSelected(video: any) { return video.id === this.selectedId; }

    onSelect(video: any) {
        //console.log(video);
        this.router.navigate(['/video', video.id]);
          window.location.reload();
    }
}