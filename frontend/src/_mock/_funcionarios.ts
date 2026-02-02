import type { IFuncionarioItem } from 'src/types/funcionario';

// ----------------------------------------------------------------------

const FUNCAO_OPTIONS = [
  'Pedreiro (horista)',
  'Servente',
  'TERRAPLANAGEM',
  'Estagiário',
  'Mestre de obra',
  'Encarregado',
  'Ajudante',
  'Eletricista',
  'Encanador',
  'Pintor',
  'Soldador',
  'Operador de máquinas',
];

const NIVEL_OPTIONS = [
  'Básico',
  'Intermediário',
  'Avançado',
  'Especialista',
  'Mestre',
];

const STATUS_OPTIONS = [
  { value: 'ativo', label: 'Ativo' },
  { value: 'inativo', label: 'Inativo' },
  { value: 'ex-funcionario', label: 'Ex-funcionário' },
];

// ----------------------------------------------------------------------

const generateId = () => Math.random().toString(36).substr(2, 9);

const generateCPF = () => {
  const numbers = Math.floor(Math.random() * 90000000000) + 10000000000;
  return numbers.toString().replace(/(\d{3})(\d{3})(\d{3})(\d{2})/, '$1.$2.$3-$4');
};

const generatePhone = () => {
  const numbers = Math.floor(Math.random() * 90000000000) + 10000000000;
  return numbers.toString().replace(/(\d{2})(\d{5})(\d{4})/, '($1) $2-$3');
};

const generateDate = () => {
  const date = new Date();
  date.setDate(Math.floor(Math.random() * 28) + 1);
  date.setMonth(Math.floor(Math.random() * 12));
  return date.toLocaleDateString('pt-BR');
};

const names = [
  'JOÃO SILVA', 'MARIA SANTOS', 'PEDRO OLIVEIRA', 'ANA COSTA', 'CARLOS PEREIRA',
  'JULIA FERREIRA', 'LUCAS RODRIGUES', 'FERNANDA ALMEIDA', 'RAFAEL MARTINS', 'CAMILA LIMA',
  'DIEGO SOUZA', 'LARISSA BARBOSA', 'GABRIEL CARDOSO', 'AMANDA GOMES', 'FELIPE NUNES',
  'BRUNA RIBEIRO', 'MARCOS CARVALHO', 'LETÍCIA DIAS', 'THIAGO MENDES', 'VANESSA FREITAS'
];

const emails = [
  'joao.silva@email.com', 'maria.santos@email.com', 'pedro.oliveira@email.com',
  'ana.costa@email.com', 'carlos.pereira@email.com', 'julia.ferreira@email.com',
  'lucas.rodrigues@email.com', 'fernanda.almeida@email.com', 'rafael.martins@email.com',
  'camila.lima@email.com', 'diego.souza@email.com', 'larissa.barbosa@email.com',
  'gabriel.cardoso@email.com', 'amanda.gomes@email.com', 'felipe.nunes@email.com',
  'bruna.ribeiro@email.com', 'marcos.carvalho@email.com', 'leticia.dias@email.com',
  'thiago.mendes@email.com', 'vanessa.freitas@email.com'
];

export const _funcionarioList: IFuncionarioItem[] = Array.from({ length: 5 }, (_, index) => ({
  id: generateId(),
  nome: names[index],
  cpf: generateCPF(),
  telefone: generatePhone(),
  funcao: FUNCAO_OPTIONS[Math.floor(Math.random() * FUNCAO_OPTIONS.length)],
  nivel: NIVEL_OPTIONS[Math.floor(Math.random() * NIVEL_OPTIONS.length)],
  status: ['ativo', 'inativo', 'ex-funcionario'][Math.floor(Math.random() * 3)] as 'ativo' | 'inativo' | 'ex-funcionario',
  aniversario: generateDate(),
  avatarUrl: `https://i.pravatar.cc/150?img=${index + 1}`,
  email: emails[index],
  dataAdmissao: generateDate(),
  dataDemissao: Math.random() > 0.7 ? generateDate() : undefined,
  empresa: `Empresa ${index + 1}`,
  endereco: `Rua ${index + 1}, ${Math.floor(Math.random() * 999) + 1}`,
  cidade: ['São Paulo', 'Rio de Janeiro', 'Belo Horizonte', 'Salvador', 'Brasília'][Math.floor(Math.random() * 5)],
  estado: ['SP', 'RJ', 'MG', 'BA', 'DF'][Math.floor(Math.random() * 5)],
  cep: `${Math.floor(Math.random() * 90000) + 10000}-${Math.floor(Math.random() * 900) + 100}`,
  rg: `${Math.floor(Math.random() * 90) + 10}.${Math.floor(Math.random() * 900) + 100}.${Math.floor(Math.random() * 900) + 100}-${Math.floor(Math.random() * 9) + 1}`,
  sexo: Math.random() > 0.5 ? 'masculino' : 'feminino',
  dataNascimento: generateDate(),
  quantidadeFilhos: Math.floor(Math.random() * 5),
  nomeMae: `Mãe do ${names[index]}`,
  nomePai: `Pai do ${names[index]}`,
  regimeTrabalho: ['CLT', 'PJ', 'Terceirizado', 'Estagiário'][Math.floor(Math.random() * 4)],
  perfil: ['Operacional', 'Supervisão', 'Gerencial', 'Diretoria'][Math.floor(Math.random() * 4)],
  observacoes: `Observações para ${names[index]}`,
  comentario: `Comentário adicional para ${names[index]}`,
}));

export { NIVEL_OPTIONS, FUNCAO_OPTIONS, STATUS_OPTIONS };
