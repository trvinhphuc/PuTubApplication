import { Injectable, OnInit } from '@angular/core';
import { Http, Response, Headers, RequestOptions, RequestOptionsArgs } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/Observable/of';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/do';

@Injectable()
export class CommentService {
    url: string;
    constructor(private http: Http) { }

    
    getComments(id: number | string) {
        this.url = 'http://localhost:8080/test/rest/comment/getComments/' + id;
        //test:this.User;
        return this.http.get(this.url)
            .map((data: Response) => {
                let response = data.json();
                //console.log(response);
                return response;
            })
    }
    setComment(cmt: any) {
        this.url = 'http://localhost:8080/test/rest/comment/add'
        let headers = new Headers([{ 'Content-Type': 'application/json' }, { 'Accept': 'application/json' }]);
        let options = new RequestOptions({ headers: headers });
        return this.http.post(this.url, cmt, options)
            .map((data: Response) => {
                let response = data.json();
                console.log(response) 
                if (response == true) {
                    return true;
                }
                if (response == false) return false;
            }

            );
    }
    delComment(cmt: any) {
        this.url = 'http://localhost:8080/test/rest/comment/del'
        let headers = new Headers([{ 'Content-Type': 'application/json' }, { 'Accept': 'application/json' }]);
        let options = new RequestOptions({ headers: headers });
        return this.http.post(this.url, cmt, options)
            .map((data: Response) => {
                let response = data.json();
                if (response == true) {
                    return true;
                }
                if (response == false) return false;
            }

            );
    }
}