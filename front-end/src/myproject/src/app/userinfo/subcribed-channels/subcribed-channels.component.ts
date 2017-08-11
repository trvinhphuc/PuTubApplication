import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';
@Component({
  selector: 'app-subcribed-channels',
  templateUrl: './subcribed-channels.component.html',
  styleUrls: ['./subcribed-channels.component.css'],
  providers:[UserService]
})
export class SubcribedChannelsComponent implements OnInit {
  subList: any[];
  current_user: any;
  constructor(
    private service: UserService,
  ) { 
    this.current_user = this.service.getCookie('userLogin');
    
  }

  ngOnInit() {
     this.service.getSubcriberList(this.current_user)
     .subscribe(data => {this.subList = data})
    //console.log(this.subList);
  }

}
