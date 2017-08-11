import { Component, OnInit, Input } from '@angular/core';
import { UserService } from "../services/user.service";
import { Router, ActivatedRoute, Params } from '@angular/router';

@Component({
  selector: 'avatar',
  templateUrl: './avatar.component.html',
  styleUrls: ['./avatar.component.css'],
  providers:[UserService]
})
export class AvatarComponent implements OnInit {
  @Input() user: any;
  imageData: any;
  userId:number|string;
  constructor(
    private userService: UserService,
    private router:Router,
  ) { }
  
  goto(){
    this.router.navigate(["channel",this.userId])
  }
  getAvatar(){
    if(this.user.id!=null)this.userId = this.user.id;
    else this.userId = this.user.id_user;
    //console.log(this.userId);
    this.userService.getAvatar(this.userId)
    .subscribe( imageData => {
        this.imageData = imageData
      })
  }
  ngOnInit() {
    this.getAvatar();
  }

}
