import type { CepModel } from 'src/models/cep-model';
import type { CnpjModel } from 'src/models/cnpj-models';

const API_URL = 'https://brasilapi.com.br/api';

export async function getCep(cep: string): Promise<CepModel> {
  const response = await fetch(`${API_URL}/cep/v1/${cep}`);

  if (!response.ok) throw new Error(`Erro ao buscar o cep: ${cep}`);

  const data = await response.json();
  return data;
}

export async function getCnpj(cnpj: string): Promise<CnpjModel> {
  const uri = `${API_URL}/cnpj/v1`;
  const response = await fetch(`${uri}/${cnpj}`);

  if (!response.ok) throw new Error(`Erro ao buscar o CNPJ: ${cnpj}`);

  const data = await response.json();
  return data;
}
