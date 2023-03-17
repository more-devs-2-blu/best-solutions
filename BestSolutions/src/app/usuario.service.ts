import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Usuario } from './usuario';
import { environment } from 'src/environments/environment';
import { JwtRequest } from './jwt-request';
import { JwtResponse } from './jwt-response';

@Injectable({
  providedIn: 'root'
})
export class UsuariosService {

  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient){}

  public getUsuarios(): Observable<Usuario[]> {
    var headers = new HttpHeaders({ 
      'Content-Type': 'application/json',
      'Authorization':'Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhQGEiLCJleHAiOjE2NzkwMjMwMzMsImlhdCI6MTY3OTAxOTQzM30.0-OUz6ghnQ9_ZiZIFg2eFAyocv5V_lRR7eo_jGhJHgv8BgAyDLX1nKaG50r1e7cz9QumbdbznguIhzDez_35kw',
      'Access-Control-Allow-Headers': 'Authorization',
      'Access-Control-Allow-Origin': "*"
    });
    return this.http.get<Usuario[]>(`${this.apiServerUrl}/usuario`, {headers: headers});
  }

  public registrar(usuario: JwtRequest): Observable<JwtResponse> {
    return this.http.post<JwtResponse>(`${this.apiServerUrl}/auth/register`, usuario);
  }

  public logar(usuario: JwtRequest): Observable<JwtResponse> {
    return this.http.post<JwtResponse>(`${this.apiServerUrl}/auth/login`, usuario);
  }

  public editar(usuario: Usuario): Observable<Usuario> {  
    usuario.situacaoCadastral = "";
    let token = sessionStorage.getItem("token");
    if (token != null){
      var headers = new HttpHeaders({ 
        'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + token,
        'dataType': 'jsonp',
      });
      console.log(headers);
      
      
      const options = { headers, withCredentials: true };
      return this.http.put<Usuario>(`${this.apiServerUrl}/usuario/${sessionStorage.getItem("usuario")}`, usuario, options );
    } else {
      throw new Error("Token ausente");
    }
  }

}
