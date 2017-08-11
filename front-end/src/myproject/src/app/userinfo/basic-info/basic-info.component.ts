import { Component, OnInit, ViewChild } from '@angular/core';
import { DxFormComponent } from 'devextreme-angular';
import { UserService } from "../../services/user.service";
import notify from 'devextreme/ui/notify';

@Component({
  selector: 'app-basic-info',
  templateUrl: './basic-info.component.html',
  styleUrls: ['./basic-info.component.css'],
  providers: [UserService]
})
export class BasicInfoComponent implements OnInit {
  @ViewChild(DxFormComponent) form: DxFormComponent;
  user: any;
  ava: any;
  current_user: any;
  constructor(
    private service: UserService,
  ) {
    this.user = {
      id: '',
      username: '',
      email: '',
      channelname: '',
      date: '',
      ava: ''
    };
    this.current_user = this.service.getCookie('userLogin');
    this.ava = {
      id: this.current_user,
    }
  }
  getUserInfo() {
    this.service.getUserInfo(this.current_user)
      .subscribe(data => { this.user = data })
  }

  onFormSubmit = function (e) {
    if(this.user.ava != '') this.user.ava = this.user.ava[0].name;
    this.service.updateUserinfo(this.user)
      .subscribe(data => {
        //console.log(data);
        if (data == true) {
          notify({
            message: "Đã chỉnh sửa thông tin thành công.",
            position: {
              my: "center",
              at: "center"
            }
          }, "success", 3000);
          setTimeout(() => {
            window.location.reload();
          }, 2000);
        }
        else alert("Can not update info");
      });

  }
  update() {
    this.onFormSubmit
  }
  ngOnInit() {
    this.getUserInfo();
  }

}
