import type { Obra } from 'src/models/obra';
import type { OptionModel } from 'src/models/optionModel';

import Box from '@mui/material/Box';
import Paper from '@mui/material/Paper';

import TableObras from './tables/table-obras';
import SearchObras from './search/search-obras';
import HeaderCadastroObra from './header/header-cadastro-obra';

const typeOptions: OptionModel[] = [
  {
    label: 'casa',
    value: 1,
  },
  {
    label: 'apartamento',
    value: 2,
  },
];

const statusOptions: OptionModel[] = [
  {
    label: 'Em andamento',
    value: 1,
  },
  {
    label: 'Em orçamento',
    value: 2,
  },
];

const rows: Obra[] = [
  {
    id: 1,
    nome: 'CASA A VENDA',
    cliente: 'BRUNO SIQUEIRA',
    tipo: 'CASA',
    codigo: 1234,
    detalhes: 'DETALHES',
    areaTotal: '125m²',
    status: 'EM ANDAMENTO',
  },
  {
    id: 2,
    nome: 'CASA A VENDA',
    cliente: 'BRUNO SIQUEIRA',
    tipo: 'CASA',
    codigo: 1234,
    detalhes: 'DETALHES',
    areaTotal: '125m²',
    status: 'EM ANDAMENTO',
  },
  {
    id: 3,
    nome: 'CASA A VENDA',
    cliente: 'BRUNO SIQUEIRA',
    tipo: 'CASA',
    codigo: 1234,
    detalhes: 'DETALHES',
    areaTotal: '125m²',
    status: 'EM ANDAMENTO',
  },
];

export default function Obras() {
  return (
    <Box p={5} component={Paper}>
      <HeaderCadastroObra />
      <SearchObras typeOptions={typeOptions} statusOptions={statusOptions} />
      <TableObras rows={rows} />
    </Box>
  );
}
