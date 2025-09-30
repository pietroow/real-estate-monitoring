import type { IClienteItem } from "src/types/cliente";

import Box from "@mui/material/Box";
import TableRow from "@mui/material/TableRow";
import TableCell from "@mui/material/TableCell";
import IconButton from "@mui/material/IconButton";

import { Iconify } from "src/components/iconify";

type Props = {
    row: IClienteItem;
    onDelete?: (id: string) => void;
    onEdit?: (cliente: IClienteItem) => void;
};

export function ClienteTableRow({ row, onDelete, onEdit }: Props) {
    const getNome = () => row.nome;

    const handleDelete = () => {
        if (onDelete && window.confirm(`Tem certeza que deseja excluir o cliente "${row.nome}"?`)) {
            onDelete(row.id);
        }
    };

    const handleEdit = () => {
        if (onEdit) {
            onEdit(row);
        }
    };

    return(
        <TableRow hover>
            <TableCell sx={{ fontWeight: 'medium' }}>{getNome()}</TableCell>
            <TableCell>{row.contato}</TableCell>
            <TableCell>{row.telefone}</TableCell>
            <TableCell>{row.email}</TableCell>
            <TableCell>{row.fonte}</TableCell>
            <TableCell align="center">
                <Box sx={{ display: 'flex', justifyContent: 'center', gap: 0.5 }}>
                    <IconButton
                        onClick={handleEdit}
                        color="primary"
                        size="small"
                        sx={{
                            '&:hover': {
                                bgcolor: 'primary.lighter',
                            }
                        }}
                    >
                        <Iconify icon="solar:pen-bold" width={20} />
                    </IconButton>
                    <IconButton
                        onClick={handleDelete}
                        color="error"
                        size="small"
                        sx={{
                            '&:hover': {
                                bgcolor: 'error.lighter',
                            }
                        }}
                    >
                        <Iconify icon="solar:trash-bin-trash-bold" width={20} />
                    </IconButton>
                </Box>
            </TableCell>
        </TableRow>
    )
}