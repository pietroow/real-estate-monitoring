import type { CepModel } from 'src/models/cep-model';

import { useState } from 'react';

import Stack from '@mui/material/Stack';
import TextField from '@mui/material/TextField';

import { getCep } from 'src/services/catch-cep-cnpj.service';

export default function FormEndereco() {
  const [cep, setCep] = useState('');
  const [cepData, setCepData] = useState<CepModel | null>(null);
  const [cepError, setCepError] = useState<string | null>(null);

  const sendCep = async () => {
    if (cep.length !== 8){
      setCepError('Cep inexistente');
      return; 
    }

    try {
      const data = await getCep(cep);
      setCepData(data);
      setCepError(null);
    } catch (error) {
      setCepData(null);
      setCepError('Cep não encontrado, ou inválido');
    }
  };
  return (
    <Stack display="flex" style={{ height: '100%' }} gap={2}>
      <Stack maxWidth="fit-content">
        <TextField
          required
          fullWidth
          onChange={(e) => setCep(e.target.value.replace(/\D/g, ''))}
          onBlur={sendCep}
          error={!!cepError}
          helperText={cepError ?? ''}
          value={cep}
          size="small"
          label="CEP"
          variant="outlined"
          placeholder="CEP"
        />
      </Stack>

      <Stack display="flex" direction="row" gap={2} maxWidth="xl">
        <TextField
          size="small"
          value={cepData?.street || ''}
          fullWidth
          label="Endereço"
          variant="outlined"
          placeholder="Endereço"
        />
        <TextField size="small" label="Número" variant="outlined" placeholder="Número" />
      </Stack>

      <Stack display="flex" direction="row" gap={2}>
        <TextField
          size="small"
          label="Complemento"
          fullWidth
          variant="outlined"
          placeholder="Complemento"
        />
        <TextField
          size="small"
          value={cepData?.neighborhood || ''}
          label="Bairro"
          fullWidth
          variant="outlined"
          placeholder="Bairro"
        />
      </Stack>

      <Stack display="flex" direction="row" gap={2}>
        <TextField
          size="small"
          value={cepData?.state || ''}
          label="Estado"
          fullWidth
          variant="outlined"
          placeholder="Estado"
        />
        <TextField
          size="small"
          value={cepData?.city || ''}
          label="Cidade"
          fullWidth
          variant="outlined"
          placeholder="Cidade"
        />
      </Stack>
    </Stack>
  );
}
