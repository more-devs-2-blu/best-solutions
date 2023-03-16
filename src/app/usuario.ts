import {v4 as uuidV4} from "uuid"
export interface Usuario{
  id: uuidV4();
  nome: string;
  email: string;
  senha: string;
  razaoSocial: string;
  nomeFantasia: string;
  responsavel: string;
  naturezaJuridica: string;
  enquadramento: string;
  tipoTributacao: string;
  capital: number;
  tipoServico: string;
  cep: string;
  estado: string;
  cidade: string;
  bairro: string;
  endereco: string;
  numero: number;
  complemento: string;
  inscricaoIptu: string;
  areaTotal: number;
  areaImovel: number;
  cpfProprietario: string;
  situacaoCadastral: string;
  nomeSocios: string;
  socioPrincipal: string;
  cnae: string;

}