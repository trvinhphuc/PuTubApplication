import { Component, OnInit } from '@angular/core';

import { UserService } from '../services/user.service';
import { SearchService } from "../services/search.service";
import { Router, ActivatedRoute, Params } from '@angular/router';

import notify from 'devextreme/ui/notify';

@Component({
  selector: 'home',
  styleUrls: ['./home-video-list.component.css'],
  templateUrl: './home-video-list.component.html',
  providers: [UserService, SearchService]
})
export class HomeVideoListComponent implements OnInit {

  users: any[];
  show_list: any[];
  count: number;
  searchValue: string;
  option: any;
  check: boolean;
  constructor(
    private UserService: UserService,
    private route: ActivatedRoute,
    private router: Router,
    private searchService: SearchService,
  ) {
    this.count = 4;
    this.searchValue = '';
    this.option = {
      user: '',
      video: '',
    }
    this.check = true;
  }
  getUsers(): void {
    this.UserService.getUsers()
      .subscribe(users => {
        this.users = users;
        this.show_list = this.users.slice(1, this.count);
      })
  }
  more() {
    this.count = this.count + 4;
    this.show_list = this.users.slice(1, this.count);

  }

  ngOnInit(): void {
    this.getUsers();
  }
  checkValidation() {
    if (this.option.user == this.option.video) {
      let msg;
      if (this.option.user != '')
        msg = "Chọn 1 thôi nha";
      else msg = "Chọn thứ bạn cần tìm nào"
      notify({
        message: msg,
        position: {
          my: "center",
          at: "center"
        }
      }, "error", 2000);
      return false;
    }
    else return true

  }
  checkEmp() {
    if (this.searchValue != null) {
      this.check = false;
    }
    if(this.searchValue == ""){
      this.check = true;
    }
  }
  searchRequest() {
    //this.searchValue = null;
    if (this.checkValidation()) {
      let op: string;
        if(this.option.user == true) op = 'user';
        if(this.option.video == true) op = 'video';
      this.router.navigate(['search',op,this.searchValue])
     // this.searchService.search(this.searchValue,this.option)
      //.subscribe( data => { let response = data; console.log(response);})
    }
  }
}