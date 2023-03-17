import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Usuario } from './usuario';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UsuariosService {

  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient){}

  public getUsuarios(): Observable<Usuario[]> {
    return this.http.get<Usuario[]>(`${this.apiServerUrl}/usuario`);
  }

  public addUsuario(usuario: Usuario): Observable<Usuario> {
    return this.http.post<Usuario>(`${this.apiServerUrl}/usuario`, usuario);
  }

}
