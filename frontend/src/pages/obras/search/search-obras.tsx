import type { OptionModel } from 'src/models/optionModel';

import Stack from '@mui/material/Stack';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import Autocomplete from '@mui/material/Autocomplete';
import InputAdornment from '@mui/material/InputAdornment';

import { SearchIcon } from 'src/theme/core/components/mui-x-data-grid';

interface SearchObrasOption {
  typeOptions: OptionModel[];
  statusOptions: OptionModel[];
}

export default function SearchObras({ typeOptions, statusOptions }: SearchObrasOption) {
  return (
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
  );
}
