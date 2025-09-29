import type { IFuncionarioTableFilters } from 'src/types/funcionario';

import { useCallback } from 'react';

import Box from '@mui/material/Box';
import Stack from '@mui/material/Stack';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';

import { Iconify } from 'src/components/iconify';

type Props = {
  filters: {
    state: IFuncionarioTableFilters;
    setState: (newState: Partial<IFuncionarioTableFilters>) => void;
  };
  onResetPage: () => void;
  onFilterStatus: (event: React.SyntheticEvent, newValue: string) => void;
  currentStatus: string;
  statusCounts: {
    all: number;
    ativo: number;
    inativo: number;
    'ex-funcionario': number;
  };
};

export function FuncionarioTableToolbar({
  filters,
  onResetPage,
  onFilterStatus,
  currentStatus,
  statusCounts
}: Props) {
  const { state, setState } = filters;

  const handleFilterName = useCallback(
    (event: React.ChangeEvent<HTMLInputElement>) => {
      setState({ name: event.target.value });
      onResetPage();
    },
    [setState, onResetPage]
  );


  return (
    <Box sx={{
      display: 'flex',
      flexDirection: { xs: 'column', sm: 'row' },
      gap: { xs: 2, sm: 0.7 },
      alignItems: { xs: 'stretch', sm: 'center' },
      mb: 3
    }}>
      <Box sx={{
        flex: { xs: '1', sm: '0 0 30%' },
        display: 'flex',
        justifyContent: { xs: 'flex-start', sm: 'flex-start' },
        p: 0.6,
        borderRadius: 1,
        bgcolor: 'grey.200'
      }}>
        <Stack direction="row" spacing={{ xs: 1, sm: 0.8 }} flexWrap={{ xs: 'wrap', sm: 'nowrap' }}>
          <Button
            variant={currentStatus === 'all' ? 'contained' : 'outlined'}
            onClick={(e) => onFilterStatus(e, 'all')}
            size="small"
            sx={{
              minWidth: 'auto',
              px: { xs: 2, sm: 1.5 },
              py: { xs: 0.8, sm: 0.5 },
              borderRadius: 2,
              textTransform: 'none',
              fontWeight: currentStatus === 'all' ? 600 : 400,
              bgcolor: currentStatus === 'all' ? 'black' : 'grey.200',
              color: currentStatus === 'all' ? 'white' : 'black',
              border: currentStatus === 'all' ? 'none' : '1px solid',
              borderColor: 'black',
              fontSize: { xs: '0.8rem', sm: '0.75rem' },
              '&:hover': {
                bgcolor: currentStatus === 'all' ? 'grey.800' : 'grey.300',
              }
            }}
          >
            Todos {statusCounts.all}
          </Button>
          <Button
            variant={currentStatus === 'ativo' ? 'contained' : 'outlined'}
            onClick={(e) => onFilterStatus(e, 'ativo')}
            size="small"
            sx={{
              minWidth: 'auto',
              px: { xs: 2, sm: 1.5 },
              py: { xs: 0.8, sm: 0.5 },
              borderRadius: 2,
              textTransform: 'none',
              fontWeight: currentStatus === 'ativo' ? 600 : 400,
              bgcolor: currentStatus === 'ativo' ? 'black' : 'grey.200',
              color: currentStatus === 'ativo' ? 'white' : 'black',
              border: currentStatus === 'ativo' ? 'none' : '1px solid',
              borderColor: 'black',
              fontSize: { xs: '0.8rem', sm: '0.75rem' },
              '&:hover': {
                bgcolor: currentStatus === 'ativo' ? 'grey.800' : 'grey.300',
              }
            }}
          >
            Ativo {statusCounts.ativo}
          </Button>
          <Button
            variant={currentStatus === 'inativo' ? 'contained' : 'outlined'}
            onClick={(e) => onFilterStatus(e, 'inativo')}
            size="small"
            sx={{
              minWidth: 'auto',
              px: { xs: 2, sm: 1.5 },
              py: { xs: 0.8, sm: 0.5 },
              borderRadius: 2,
              textTransform: 'none',
              fontWeight: currentStatus === 'inativo' ? 600 : 400,
              bgcolor: currentStatus === 'inativo' ? 'black' : 'grey.200',
              color: currentStatus === 'inativo' ? 'white' : 'black',
              border: currentStatus === 'inativo' ? 'none' : '1px solid',
              borderColor: 'black',
              fontSize: { xs: '0.8rem', sm: '0.75rem' },
              '&:hover': {
                bgcolor: currentStatus === 'inativo' ? 'grey.800' : 'grey.300',
              }
            }}
          >
            Inativo {statusCounts.inativo}
          </Button>
          <Button
            variant={currentStatus === 'ex-funcionario' ? 'contained' : 'outlined'}
            onClick={(e) => onFilterStatus(e, 'ex-funcionario')}
            size="small"
            sx={{
              minWidth: 'auto',
              px: { xs: 2, sm: 1.5 },
              py: { xs: 0.8, sm: 0.5 },
              borderRadius: 2,
              textTransform: 'none',
              fontWeight: currentStatus === 'ex-funcionario' ? 600 : 400,
              bgcolor: currentStatus === 'ex-funcionario' ? 'black' : 'grey.200',
              color: currentStatus === 'ex-funcionario' ? 'white' : 'black',
              border: currentStatus === 'ex-funcionario' ? 'none' : '1px solid',
              borderColor: 'black',
              fontSize: { xs: '0.8rem', sm: '0.75rem' },
              '&:hover': {
                bgcolor: currentStatus === 'ex-funcionario' ? 'grey.800' : 'grey.300',
              }
            }}
          >
            Ex-funcionário {statusCounts['ex-funcionario']}
          </Button>
        </Stack>
      </Box>

      <Box sx={{ flex: { xs: '1', sm: '0 0 70%' } }}>
        <TextField
          fullWidth
          size="small"
          placeholder="Pesquisar por nome, email..."
          value={state.name}
          onChange={handleFilterName}
          InputProps={{
            startAdornment: (
              <Iconify
                icon="eva:search-fill"
                sx={{ color: 'text.disabled', width: 20, height: 20, mr: 1 }}
              />
            ),
          }}
        />
      </Box>
    </Box>
  );
}
