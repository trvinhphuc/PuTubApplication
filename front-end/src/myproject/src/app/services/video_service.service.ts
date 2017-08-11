import { Injectable, OnInit } from '@angular/core';
import { Http, Response, Headers, RequestOptions, RequestOptionsArgs } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/Observable/of';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/do';
import 'rxjs/add/operator/toPromise';

@Injectable()
export class VideoService {
    url: string;
    Video: any;
    constructor(
        private http: Http
    ) {
        this.Video = {
            id: '',
            src: '',
            name: ''
        };
    }
    getVid(id: number | string) {
        this.url = 'http://localhost:8080/test/rest/video2/' + id;
        return this.http.get(this.url)
            .map((data: Response) => {
                let response = data.json();
                this.Video = response;
                return this.Video;
            });

    }
    getVideoList(id: number | string) {
        this.url = 'http://localhost:8080/test/rest/video/getVideoList/' + id;
        return this.http.get(this.url)
            .map((data: Response) => {
                let response = data.json();
                //console.log(response);
                return response;
            })
    }
    sendVideo(video: any) {
        this.url = 'http://localhost:8080/test/rest/video3/upload'
        let headers = new Headers([{ 'Content-Type': 'application/json' }, { 'Accept': 'application/json' }]);
        let options = new RequestOptions({ headers: headers });
        return this.http.post(this.url, video, options)
            .map((data: Response) => {
                let response = data.json();
                //console.log(response);
                //return response;
                if (response == true) return true;
                if (response == false) return false;
            });
    }
    check_privacy(video_id: number, user_id: number) {
        this.url = 'http://localhost:8080/test/rest/checkPriv?video_id=' + video_id + '&user_id=' + user_id;
        return this.http.get(this.url)
            .map((data: Response) => {
                let response = data.json();
                //console.log(response);
                return response;
            })
    }
    deleteVideo(id: number) {
        this.url = 'http://localhost:8080/test/rest/delete/' + id;
        return this.http.get(this.url)
            .map((data: Response) => {
                let response = data.json();
                //console.log(response);
                return response;
            })
    }
    updateVideo(video: any) {
        this.url = 'http://localhost:8080/test/rest/video4/update'
        let headers = new Headers([{ 'Content-Type': 'application/json' }, { 'Accept': 'application/json' }]);
        let options = new RequestOptions({ headers: headers });
        return this.http.post(this.url, video, options)
            .map((data: Response) => {
                let response = data.json();
                //console.log(response);
                //return response;
                if (response == true) return true;
                if (response == false) return false;
            });
    }
    likevideo() {

    }
    dislikevideo() {

    }
}