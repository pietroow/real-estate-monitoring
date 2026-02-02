import type { IFuncionarioTableFilters } from 'src/types/funcionario';

import { useCallback } from 'react';

import Box from '@mui/material/Box';
import Chip from '@mui/material/Chip';
import Stack from '@mui/material/Stack';

import { Iconify } from 'src/components/iconify';

type Props = {
  filters: {
    state: IFuncionarioTableFilters;
    setState: (newState: Partial<IFuncionarioTableFilters>) => void;
  };
  onResetPage: () => void;
  totalResults: number;
  sx?: object;
};

export function FuncionarioTableFiltersResult({
  filters,
  onResetPage,
  totalResults,
  sx,
}: Props) {
  const { state, setState } = filters;

  const handleRemoveStatus = useCallback(() => {
    setState({ status: 'all' });
    onResetPage();
  }, [setState, onResetPage]);


  const handleRemoveName = useCallback(() => {
    setState({ name: '' });
    onResetPage();
  }, [setState, onResetPage]);

  return (
    <Stack spacing={1.5} sx={{ p: 3, ...sx }}>
      <Box sx={{ typography: 'body2' }}>
        <strong>{totalResults}</strong>
        <Box component="span" sx={{ color: 'text.secondary', ml: 0.25 }}>
          resultados encontrados
        </Box>
      </Box>

      <Stack flexWrap="wrap" direction="row" spacing={1}>
        {state.status !== 'all' && (
          <Chip
            size="small"
            label="Status"
            onDelete={handleRemoveStatus}
            deleteIcon={
              <Iconify width={16} icon="solar:close-circle-bold" />
            }
          />
        )}

        {state.name && (
          <Chip
            size="small"
            label="Nome"
            onDelete={handleRemoveName}
            deleteIcon={
              <Iconify width={16} icon="solar:close-circle-bold" />
            }
          />
        )}

      </Stack>
    </Stack>
  );
}
