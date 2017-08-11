import { Component, OnInit,ViewChild } from '@angular/core';
import { DxFormComponent } from 'devextreme-angular';
import notify from 'devextreme/ui/notify';
import { UserService } from "../../services/user.service";

@Component({
  selector: 'app-password-change',
  templateUrl: './password-change.component.html',
  styleUrls: ['./password-change.component.css'],
  providers:[UserService],
})
export class PasswordChangeComponent implements OnInit {
  @ViewChild(DxFormComponent) form: DxFormComponent;
  user: any
  current_user:any;
  password = '';
  passwordOptions: any = {
    mode: "password",
    value: this.password
  };
  constructor(
    private service: UserService,
  ) {
    this.user = {
      id: '',
      username: '',
      password: '',
      newpass: '',
      email: '',
      channelname: '',
      date: '',
      ava: ''
    };
    this.current_user = this.service.getCookie('userLogin')
  }
  passwordComparison = () => {
    return this.form.instance.option("formData").newpass;
  }
  checkComparison() {
    return true;
  }
  onFormSubmit = function (e) {
    this.user.id = this.current_user;
    this.service.changePass(this.user)
        .subscribe(data => {
        //console.log(data);
        if (data == true) {
          notify({
            message: "Đã chỉnh sửa mật khẩu thành công.",
            position: {
              my: "center",
              at: "center"
            }
          }, "success", 3000);
          setTimeout(() => {
            window.location.reload();
          }, 2000);
        }
        else alert("Can not change password");
      });

  }
  updatePass(){
    this.onFormSubmit;
  }
  ngOnInit() {
  }

}
