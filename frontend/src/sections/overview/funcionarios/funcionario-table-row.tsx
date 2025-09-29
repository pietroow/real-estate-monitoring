import type { IFuncionarioItem } from 'src/types/funcionario';

import { useBoolean } from 'minimal-shared/hooks';

import Link from '@mui/material/Link';
import Button from '@mui/material/Button';
import Tooltip from '@mui/material/Tooltip';
import TableRow from '@mui/material/TableRow';
import Checkbox from '@mui/material/Checkbox';
import TableCell from '@mui/material/TableCell';
import IconButton from '@mui/material/IconButton';

import { RouterLink } from 'src/routes/components';

import { Label } from 'src/components/label';
import { Iconify } from 'src/components/iconify';
import { ConfirmDialog } from 'src/components/custom-dialog';

type Props = {
  row: IFuncionarioItem;
  selected: boolean;
  editHref: string;
  onSelectRow: () => void;
  onDeleteRow: () => void;
  onEditRow?: (funcionario: IFuncionarioItem) => void;
};

export function FuncionarioTableRow({ row, selected, editHref, onSelectRow, onDeleteRow, onEditRow }: Props) {
  const confirmDialog = useBoolean();

  const renderConfirmDialog = () => (
    <ConfirmDialog
      open={confirmDialog.value}
      onClose={confirmDialog.onFalse}
      title="Excluir"
      content="Tem certeza que deseja excluir este funcionário?"
      action={
        <Button variant="contained" color="error" onClick={onDeleteRow}>
          Excluir
        </Button>
      }
    />
  );

  return (
    <>
      <TableRow hover selected={selected}>
        <TableCell padding="checkbox">
          <Checkbox checked={selected} onClick={onSelectRow} />
        </TableCell>

        <TableCell>
          <Link
            component={RouterLink}
            href={editHref}
            color="inherit"
            variant="subtitle2"
            noWrap
          >
            {row.nome}
          </Link>
        </TableCell>

        <TableCell>{row.telefone}</TableCell>

        <TableCell>{row.funcao}</TableCell>

        <TableCell>{row.nivel}</TableCell>

        <TableCell>
          <Label
            variant="soft"
            color={
              (row.status === 'ativo' && 'success') ||
              (row.status === 'inativo' && 'warning') ||
              (row.status === 'ex-funcionario' && 'error') ||
              'default'
            }
          >
            {row.status === 'ativo' && 'Ativo'}
            {row.status === 'inativo' && 'Inativo'}
            {row.status === 'ex-funcionario' && 'Ex-funcionário'}
          </Label>
        </TableCell>

        <TableCell>{row.aniversario}</TableCell>

        <TableCell align="right">
          <Tooltip title="Editar">
            <IconButton onClick={() => onEditRow?.(row)}>
              <Iconify icon="solar:pen-bold" />
            </IconButton>
          </Tooltip>

          <Tooltip title="Excluir">
            <IconButton onClick={() => confirmDialog.onTrue()}>
              <Iconify icon="solar:trash-bin-trash-bold" />
            </IconButton>
          </Tooltip>
        </TableCell>
      </TableRow>

      {renderConfirmDialog()}
    </>
  );
}
