import type { IClienteTableFilters } from "src/types/cliente";

import Stack from "@mui/material/Stack";
import Button from "@mui/material/Button";
import TextField from "@mui/material/TextField";

import { Iconify } from "src/components/iconify";

type Props = {
    filters: IClienteTableFilters;
    onFilters: (key: keyof IClienteTableFilters, value: string) => void;
};

export function ClienteTableToolbar({ filters, onFilters }: Props) {
    return(
        <Stack direction="row" alignItems="center" spacing={2} sx={{ p: 2 }}>
            <TextField
                placeholder="Digite aqui sua busca"
                value={filters.name}
                onChange={(e) => onFilters('name', e.target.value)}
                sx={{
                    width: 400,
                    '& .MuiOutlinedInput-root': {
                        borderRadius: 2,
                    }
                }}
                size="small"
                InputProps={{
                    startAdornment: <Iconify icon="eva:search-fill" sx={{ color: 'text.disabled', mr: 1 }} />
                }}
            />

            <Stack direction="row" spacing={1}>
                <Button
                    variant={filters.status === 'ativo' ? 'contained' : 'outlined'}
                    color="info"
                    size="small"
                    onClick={() => onFilters('status', filters.status === 'ativo' ? 'all' : 'ativo')}
                    sx={{
                        minWidth: 80,
                        borderRadius: 2,
                        textTransform: 'none',
                        fontWeight: 'medium',
                    }}
                >
                    Ativo
                </Button>
                <Button
                    variant={filters.status === 'inativo' ? 'contained' : 'outlined'}
                    color="info"
                    size="small"
                    onClick={() => onFilters('status', filters.status === 'inativo' ? 'all' : 'inativo')}
                    sx={{
                        minWidth: 80,
                        borderRadius: 2,
                        textTransform: 'none',
                        fontWeight: 'medium',
                    }}
                >
                    Inativo
                </Button>
            </Stack>
        </Stack>
    );
}