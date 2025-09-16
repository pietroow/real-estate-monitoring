import type { Obra } from 'src/models/obra';

import { useState } from 'react';

import Box from '@mui/material/Box';
import Paper from '@mui/material/Paper';
import Table from '@mui/material/Table';
import { styled } from '@mui/material/styles';
import TableBody from '@mui/material/TableBody';
import TableHead from '@mui/material/TableHead';
import TableContainer from '@mui/material/TableContainer';
import TablePagination from '@mui/material/TablePagination';
import TableRow, { tableRowClasses } from '@mui/material/TableRow';
import TableCell, { tableCellClasses } from '@mui/material/TableCell';

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

interface ObrasTableProps {
  rows: Obra[];
}

export default function TableObras({ rows }: ObrasTableProps) {
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
    <Paper>
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
                    <span style={{ color: '#636363' }} className="material-icons">
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
        sx={{
          '.MuiTablePagination-toolbar': {
            alignItems: 'baseline',
          },
        }}
        rowsPerPageOptions={[5, 10, 25]}
        component="div"
        count={rows.length}
        rowsPerPage={rowsPerPage}
        page={page}
        onPageChange={handleChangePage}
        onRowsPerPageChange={handleChangeRowsPerPage}
      />
    </Paper>
  );
}
