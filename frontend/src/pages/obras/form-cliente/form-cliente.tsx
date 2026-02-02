import type { CnpjModel } from 'src/models/cnpj-models';

import { useState } from 'react';

import Radio from '@mui/material/Radio';
import Stack from '@mui/material/Stack';
import FormLabel from '@mui/material/FormLabel';
import TextField from '@mui/material/TextField';
import RadioGroup from '@mui/material/RadioGroup';
import Autocomplete from '@mui/material/Autocomplete';
import FormControlLabel from '@mui/material/FormControlLabel';

import { getCnpj } from 'src/services/catch-cep-cnpj.service';

export default function FormCliente() {
  const [cnpj, setCnpj] = useState('');
  const [cnpjData, setCnpjData] = useState<CnpjModel | null>(null);
  const [cnpjError, setCnpjError] = useState<string | null>(null);

  const sendCnpj = async () => {
    if (cnpj.length !== 14) {
      setCnpjError('Cnpj inexistente');
      return;
    }

    try {
      const data = await getCnpj(cnpj);
      setCnpjData(data);
      console.log('cnpj', data);
    } catch (error) {
      setCnpjData(null);
      setCnpjError('Cnpj não encontrado, ou inválido');
    }
  };

  return (
    <Stack display="flex" style={{ height: '100%' }} direction="column" gap={2}>
      <Autocomplete
        size="small"
        disablePortal
        renderInput={(params) => <TextField {...params} label="Cliente" />}
        options={[]}
        fullWidth
      />
      <Stack display="flex" direction="row" gap={2} alignItems="center">
        <Stack width={300} paddingLeft={1}>
          <FormLabel id="demo-row-radio-buttons-group-label">Tipo:</FormLabel>
          <RadioGroup
            row
            aria-labelledby="demo-row-radio-buttons-group-label"
            name="row-radio-buttons-group"
          >
            <FormControlLabel value="PJ" control={<Radio />} label="Pessoa jurídica" />
            <FormControlLabel value="PF" control={<Radio />} label="Pessoa física" />
          </RadioGroup>
        </Stack>

        <TextField
          sx={{ width: 400 }}
          value={cnpj}
          onChange={(e) => setCnpj(e.target.value.replace(/\D/g, ''))}
          onBlur={sendCnpj}
          error={!!cnpjError}
          helperText={cnpjError ?? ''}
          size="small"
          required
          label="CNPJ"
          placeholder="Digite aqui a sua busca"
        />
      </Stack>

      <Stack display="flex" direction="row" gap={2}>
        <TextField
          size="small"
          label="Inscrição estadual"
          fullWidth
          variant="outlined"
          placeholder="Inscrição estadual"
        />
        <TextField
          size="small"
          label="Inscrição municipal"
          fullWidth
          variant="outlined"
          placeholder="Inscrição municipal"
        />
      </Stack>

      <Stack display="flex" direction="row" gap={2}>
        <TextField
          size="small"
          label="E-mail"
          value={cnpjData?.email || ''}
          fullWidth
          variant="outlined"
          placeholder="E-mail"
        />
        <TextField
          size="small"
          label="Telefone"
          value={cnpjData?.ddd_telefone_1 || ''}
          fullWidth
          variant="outlined"
          placeholder="Telefone"
        />
      </Stack>
    </Stack>
  );
}
