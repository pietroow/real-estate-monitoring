import type { Obra } from 'src/models/obra';
import type { OptionModel } from 'src/models/optionModel';

import { useState } from 'react';

import Box from '@mui/material/Box';
import Stack from '@mui/material/Stack';
import Table from '@mui/material/Table';
import Paper from '@mui/material/Paper';
import Button from '@mui/material/Button';
import { styled } from '@mui/material/styles';
import TableBody from '@mui/material/TableBody';
import TableHead from '@mui/material/TableHead';
import TextField from '@mui/material/TextField';
import Typography from '@mui/material/Typography';
import Autocomplete from '@mui/material/Autocomplete';
import InputAdornment from '@mui/material/InputAdornment';
import TableContainer from '@mui/material/TableContainer';
import TablePagination from '@mui/material/TablePagination';
import TableRow, { tableRowClasses } from '@mui/material/TableRow';
import TableCell, { tableCellClasses } from '@mui/material/TableCell';

import { SearchIcon } from 'src/theme/core/components/mui-x-data-grid';

const StyledTableCell = styled(TableCell)(({ theme }) => ({
  [`&.${tableCellClasses.head}`]: {
    background: theme.palette.common.white,
    color: theme.palette.common.black,
  },
}));

const StyledTableRow = styled(TableRow)(({ theme }) => ({
  '&:nth-of-type(odd)': {
    background: theme.palette.action.hover,
  },
  [`&.${tableRowClasses.head}`]: {
    color: '#636363',
  },
}));

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
  const [page, setPage] = useState(0);
  const [rowsPerPage, setRowsPerPage] = useState(5);

  const handleChangePage = (event: unknown, newPage: number) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = (event: React.ChangeEvent<HTMLInputElement>) => {
    setRowsPerPage(parseInt(event.target.value, 10));
    setPage(0);
  };

  return (
    <Box p={5} component={Paper}>
      <Stack paddingBottom={3} display="flex" flexDirection="row" justifyContent="space-between">
        <Typography variant="h4" color="#636363" gutterBottom>
          Cadastro de Obras
        </Typography>
        <Box display="flex" gap={2}>
          <Button
            style={{
              color: '#ffff',
              backgroundColor: '#00e676',
              border: '#69f0ae 1px solid',
              borderRadius: '8px',
              alignItems: 'center',
            }}
            variant="outlined"
          >
            <span
              className="material-icons"
              style={{
                cursor: 'pointer',
                alignItems: 'end',
                fontSize: '16px',
                color: '#ffff',
              }}
            >
              add
            </span>
            Novo
          </Button>
          <Button
            style={{
              backgroundColor: '#81d4fa',
              borderRadius: '8px',
              alignItems: 'center',
            }}
            variant="outlined"
          >
            <span
              className="material-icons"
              style={{
                cursor: 'pointer',
                alignItems: 'end',
                fontSize: '16px',
                color: '#ffff',
              }}
            >
              print
            </span>
          </Button>
        </Box>
      </Stack>

      <Stack paddingBottom={1} display="flex" flexDirection="row" gap={2}>
        <TextField
          sx={{ width: 300 }}
          variant="outlined"
          placeholder="Digite aqui a sua busca"
          slotProps={{
            input: {
              startAdornment: (
                <InputAdornment position="start">
                  <SearchIcon />
                </InputAdornment>
              ),
            },
          }}
        />

        <Autocomplete
          disablePortal
          options={typeOptions.map((option) => option.label)}
          sx={{ width: 300 }}
          renderInput={(params) => <TextField {...params} label="Tipo" />}
        />
        <Autocomplete
          disablePortal
          options={statusOptions.map((option) => option.label)}
          sx={{ width: 300 }}
          renderInput={(params) => <TextField {...params} label="Status" />}
        />
        <Button
          style={{
            backgroundColor: '#81d4fa',
            borderRadius: '8px',
            alignItems: 'center',
          }}
          size="small"
          variant="outlined"
        >
          <span
            className="material-icons"
            style={{
              cursor: 'pointer',
              alignItems: 'end',
              fontSize: '16px',
              color: '#ffff',
            }}
          >
            search
          </span>
        </Button>
      </Stack>

      <TableContainer>
        <Table>
          <TableHead>
            <TableRow>
              <StyledTableCell>Obra</StyledTableCell>
              <StyledTableCell>Cliente</StyledTableCell>
              <StyledTableCell>Tipo</StyledTableCell>
              <StyledTableCell>Código</StyledTableCell>
              <StyledTableCell>Visível para</StyledTableCell>
              <StyledTableCell>Área Total</StyledTableCell>
              <StyledTableCell>Status</StyledTableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {rows.map((row) => (
              <StyledTableRow key={row?.id}>
                <StyledTableCell>{row?.nome}</StyledTableCell>
                <StyledTableCell>{row?.cliente}</StyledTableCell>
                <StyledTableCell>{row?.tipo}</StyledTableCell>
                <StyledTableCell>{row?.codigo}</StyledTableCell>
                <StyledTableCell>
                  <Box display="flex" alignItems="center" gap={1}>
                    {row.detalhes}
                    <span
                      className="material-icons"
                      style={{
                        cursor: 'pointer',
                        alignItems: 'end',
                        fontSize: '16px',
                        color: '#636363',
                      }}
                    >
                      visibility
                    </span>
                  </Box>
                </StyledTableCell>
                <StyledTableCell>{row.areaTotal}</StyledTableCell>
                <StyledTableCell>{row.status}</StyledTableCell>
              </StyledTableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
      <TablePagination
        rowsPerPageOptions={[5, 10, 25]}
        component="div"
        count={rows.length}
        rowsPerPage={rowsPerPage}
        page={page}
        onPageChange={handleChangePage}
        onRowsPerPageChange={handleChangeRowsPerPage}
      />
    </Box>
  );
}
