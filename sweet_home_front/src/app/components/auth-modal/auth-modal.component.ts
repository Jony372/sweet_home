import { Component, inject, output, signal } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { form, required, FormField } from '@angular/forms/signals';
import { RegisterReqModel } from '../../models/register-req.model';

@Component({
  selector: 'app-auth-modal',
  imports: [FormField],
  templateUrl: './auth-modal.component.html',
  styleUrl: './auth-modal.component.css',
})
export class AuthModalComponent {
  authService = inject(AuthService);
  close = output<void>();

  public data = signal({
    username: '',
    name: '',
    lastName: '',
    email: '',
    phone: '',
    password: '',
    rememberMe: false,
  })

  public authForm = form(this.data, (data)=> {
    required(data.username, {
      message: 'El nombre de usuario es obligatorio.'
    });
    required(data.password, {
      message: 'La contraseña es obligatoria.'
    });
  })

  isLoginMode = signal<boolean>(true);

  toggleMode() {
    this.isLoginMode.update((current) => !current);
  }

  closeModal() {
    this.close.emit();
  }

  onSubmit(event: SubmitEvent) {
    if(event) event.preventDefault();

    if(this.authForm().valid()){
      const username = this.authForm.username().value();
      const password = this.authForm.password().value();
      if(this.isLoginMode()){
        const rememberMe = this.authForm.rememberMe().value();
        this.authService.login(username, password, rememberMe).subscribe({
          next: (response) => this.closeModal()
        });
      }else{
        const reqData: RegisterReqModel = {
          username: this.authForm.username().value(),
          name: this.authForm.name().value(),
          lastName: this.authForm.lastName().value(),
          email: this.authForm.email().value(),
          phone: this.authForm.phone().value(),
          password: this.authForm.password().value()
        };
        this.authService.register(reqData).subscribe({
          next: () => {
            this.isLoginMode.set(true);
            this.authForm().value().password = '';
          }
        });
      }
    }
  }
}
