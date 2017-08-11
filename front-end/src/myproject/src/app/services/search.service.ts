import { Injectable, OnInit } from '@angular/core';
import { Http, Response, Headers, RequestOptions, RequestOptionsArgs } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/Observable/of';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';

@Injectable()
export class SearchService {

    url: string;
    constructor(
        private http: Http
    ) {

    }
    search(param : string, option : any ) {
        
        this.url = 'http://localhost:8080/test/rest/search/'+option+'/'+param;
        return this.http.get(this.url)
            .map((data: Response) => {
                let response = data.json();
                //console.log(response);
                return response;
            })
    }
}