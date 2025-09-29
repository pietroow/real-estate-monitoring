export type IClienteItem = {
    id: string;
    tipo?: 'juridica' | 'fisica';
    nome: string;
    razaoSocial?: string;
    cnpj?: string;
    inscricaoEstadual?: string;
    inscricaoMunicipal?: string;
    cpf?: string;
    rg?: string;
    telefone: string;
    telefone2?: string;
    email: string;
    fornecedor?: 'sim' | 'nao';
    status: 'ativo' | 'inativo';

    cep?: string;
    endereco?: string;
    numero?: string;
    complemento?: string;
    bairro?: string;
    estado?: string;
    cidade?: string;

    fonte: string;
    setor: string;
    contaBancaria?: string;

    contato: string;
    contato1Nome?: string;
    contato1Email?: string;
    contato1Cargo?: string;
    contato1Telefone?: string;
    contato1Aniversario?: string;
    contato2Nome?: string;
    contato2Email?: string;
    contato2Cargo?: string;
    contato2Telefone?: string;
    contato2Aniversario?: string;

    arquivos?: File[];
    comentarios?: string;

    createdAt: Date;
};

export type IClienteTableFilters = {
    name: string;
    status: 'all' | 'ativo' | 'inativo';
}