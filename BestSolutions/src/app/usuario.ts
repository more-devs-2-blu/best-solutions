export interface Usuario{
  id: number;
  nome: string;
  email: string;
  senha: string;
  razaoSocial: string | null;
  nomeFantasia: string | null;
  responsavel: string | null;
  naturezaJuridica: string | null;
  enquadramento: string | null;
  tipoTributacao: string | null;
  capital: number | null;
  tipoServico: string | null;
  cep: string | null;
  estado: string | null;
  cidade: string | null;
  bairro: string | null;
  endereco: string | null;
  numero: number | null;
  complemento: string | null;
  inscricaoIptu: string | null;
  areaTotal: number | null;
  areaImovel: number | null;
  cpfProprietario: string | null;
  situacaoCadastral: string | null;
  nomeSocios: string | null;
  socioPrincipal: string | null;
  cnae: string | null;

}