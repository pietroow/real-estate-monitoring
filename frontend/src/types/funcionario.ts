// ----------------------------------------------------------------------

export type IFuncionarioItem = {
  id: string;
  nome: string;
  cpf: string;
  telefone: string;
  funcao: string;
  nivel: string;
  status: 'ativo' | 'inativo' | 'ex-funcionario';
  aniversario: string;
  avatarUrl?: string;
  email?: string;
  dataAdmissao?: string;
  dataDemissao?: string;
  empresa?: string;
  endereco?: string;
  cidade?: string;
  estado?: string;
  cep?: string;
  rg?: string;
  sexo?: 'masculino' | 'feminino';
  dataNascimento?: string;
  quantidadeFilhos?: number;
  nomeMae?: string;
  nomePai?: string;
  regimeTrabalho?: string;
  perfil?: string;
  observacoes?: string;
  comentario?: string;
  arquivos?: any[];
  // Referências
  empresaRef1?: string;
  contatoRef1?: string;
  telefoneRef1?: string;
  empresaRef2?: string;
  contatoRef2?: string;
  telefoneRef2?: string;
};

export type IFuncionarioTableFilters = {
  name: string;
  status: string;
};

export type IFuncionarioCard = {
  id: string;
  name: string;
  avatarUrl: string;
  role: string;
  status: string;
};
