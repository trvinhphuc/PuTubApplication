import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions, RequestOptionsArgs } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/Observable/of';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/do';
import 'rxjs/add/operator/toPromise';

@Injectable()
export class UserService {
  url: string;
  //islogin: boolean;
  User: any;
  constructor(
    private http: Http
  ) {
    this.User = {
      id: '',
      ava: ''
    };
  }
  getUsers() {
    this.url = 'http://localhost:8080/test/rest/user/getUserList';
    //test:this.User;
    return this.http.get(this.url)
      .map((data: Response) => {
        let response = data.json();
        //console.log(response);
        return response;
      })
  }

  login(user: any) {
    this.url = 'http://localhost:8080/test/rest/test/login'
    let headers = new Headers([{ 'Content-Type': 'application/json' }, { 'Accept': 'application/json' }]);
    let options = new RequestOptions({ headers: headers });
    return this.http.post(this.url, user, options)
      .map((data: Response) => {
        let response = data.json();
        //console.log(response);
        //return response;
        if (response == "false") return false;
        else {
          sessionStorage.setItem('userinfo', JSON.stringify(response));
          this.setCookie("userLogin", response.id, 10);
          return true;
        }

      });

  }
  logup(user: any) {
    this.url = 'http://localhost:8080/test/rest/test2/logup'
    let headers = new Headers([{ 'Content-Type': 'application/json' }, { 'Accept': 'application/json' }]);
    let options = new RequestOptions({ headers: headers });
    return this.http.post(this.url, user, options)
      .map((data: Response) => {
        let response = data.json();
        //console.log(response);
        //return response;
        if (response == false) return false
        else {
          this.setCookie("userLogin", response.id, 10);
          return true;
        }
      });
  }
  getAvatar(id: number | string) {
    this.url = 'http://localhost:8080/test/rest/ava/' + id;
    return this.http.get(this.url)
      .map((data: Response) => {
        let response = data.json();
        this.User = response;
        return 'data:image/jpg;base64,' + this.User.avatar;
      });
  }
  updateUserinfo(user: any) {
    this.url = 'http://localhost:8080/test/rest/user2/update'
    let headers = new Headers([{ 'Content-Type': 'application/json' }, { 'Accept': 'application/json' }]);
    let options = new RequestOptions({ headers: headers });
    return this.http.post(this.url, user, options)
      .map((data: Response) => {
        let response = data.json();
        //console.log(response);
        //return response;
        if (response == true) {

          return true;
        }
        if (response == false) return false;
      });
  }
  getUserInfo(id: number | string) {
    this.url = 'http://localhost:8080/test/rest/user2/getUserInfo/' + id;
    return this.http.get(this.url)
      .map((data: Response) => {
        let response = data.json();
        this.User = response;
        return this.User;
      })
  }
  setSubcriber(sub: any) {
    this.url = 'http://localhost:8080/test/rest/sub/add'
    let headers = new Headers([{ 'Content-Type': 'application/json' }, { 'Accept': 'application/json' }]);
    let options = new RequestOptions({ headers: headers });
    return this.http.post(this.url, sub, options)
      .map((data: Response) => {
        let response = data.json();
        if (response == true) {
          return true;
        }
        if (response == false) return false;
      }
      );
  }
  delSubcriber(sub: any) {
    this.url = 'http://localhost:8080/test/rest/sub/del'
    let headers = new Headers([{ 'Content-Type': 'application/json' }, { 'Accept': 'application/json' }]);
    let options = new RequestOptions({ headers: headers });
    return this.http.post(this.url, sub, options)
      .map((data: Response) => {
        let response = data.json();
        if (response == true) {
          return true;
        }
        if (response == false) return false;
      }

      );
  }
  getSubcriberList(id: number | string) {
    this.url = 'http://localhost:8080/test/rest/sub/getSubList/' + id;
    return this.http.get(this.url)
      .map((data: Response) => {
        let response = data.json();
        //console.log(response);
        return response;
      })
  }
  changePass(user: any) {
    this.url = 'http://localhost:8080/test/rest/user2/changePass'
    let headers = new Headers([{ 'Content-Type': 'application/json' }, { 'Accept': 'application/json' }]);
    let options = new RequestOptions({ headers: headers });
    return this.http.post(this.url, user, options)
      .map((data: Response) => {
        let response = data.json();
        if (response == true) {
          return true;
        }
        if (response == false) return false;
      }
      );
  }
  private handleError(error: any) {
    console.error('An error occurred', error); // for demo purposes only
    return Observable.throw(error.message || error);
  }
  private extractData(res: Response) {
    let body = res.json();
    return body.data || {};
  }
  setCookie(cname, cvalue, exdays) {
    var d = new Date();
    d.setTime(d.getTime() + (exdays * 60 * 60 * 1000));
    var expires = "expires=" + d.toUTCString();
    document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
  }
  getCookie(name: string) {
    let ca: Array<string> = document.cookie.split(';');
    let caLen: number = ca.length;
    let cookieName = `${name}=`;
    let c: string;

    for (let i: number = 0; i < caLen; i += 1) {
      c = ca[i].replace(/^\s+/g, '');
      if (c.indexOf(cookieName) == 0) {
        return c.substring(cookieName.length, c.length);
      }
    }
    return '';
  }

}
