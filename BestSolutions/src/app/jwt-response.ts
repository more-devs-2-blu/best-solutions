import { Usuario } from "./usuario";

export interface JwtResponse {

    jwtToken: string;
    usuarioDto: Usuario;
}
