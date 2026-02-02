import type { IClienteItem } from 'src/types/cliente';

import Box from '@mui/material/Box';
import Grid from '@mui/material/Grid';
import MenuItem from '@mui/material/MenuItem';
import TextField from '@mui/material/TextField';
import Typography from '@mui/material/Typography';

type Props = {
  data: Partial<IClienteItem>;
  onChange: (data: Partial<IClienteItem>) => void;
  errors?: Record<string, boolean>;
};

const ESTADOS = [
  'AC', 'AL', 'AP', 'AM', 'BA', 'CE', 'DF', 'ES', 'GO', 'MA',
  'MT', 'MS', 'MG', 'PA', 'PB', 'PR', 'PE', 'PI', 'RJ', 'RN',
  'RS', 'RO', 'RR', 'SC', 'SP', 'SE', 'TO'
];

export function ClienteFormStep2({ data, onChange }: Props) {
  const handleChange = (field: string, value: any) => {
    onChange({ [field]: value } as any);
  };

  return (
    <Box>
      <Typography variant="h6" sx={{ mb: 3, color: 'text.primary' }}>
        Endereço
      </Typography>

      <Grid container spacing={3}>
        <Grid size={4}>
          <TextField
            fullWidth
            label="CEP"
            value={data.cep || ''}
            onChange={(e) => handleChange('cep', e.target.value)}
            placeholder="00000-000"
          />
        </Grid>
        <Grid size={8}>
          <TextField
            fullWidth
            label="Endereço"
            value={data.endereco || ''}
            onChange={(e) => handleChange('endereco', e.target.value)}
            placeholder="Rua, Avenida, etc."
          />
        </Grid>

        <Grid size={4}>
          <TextField
            fullWidth
            label="Número"
            value={data.numero || ''}
            onChange={(e) => handleChange('numero', e.target.value)}
            placeholder="123"
          />
        </Grid>
        <Grid size={8}>
          <TextField
            fullWidth
            label="Complemento"
            value={data.complemento || ''}
            onChange={(e) => handleChange('complemento', e.target.value)}
            placeholder="Apto, Sala, etc."
          />
        </Grid>

        <Grid size={6}>
          <TextField
            fullWidth
            label="Bairro"
            value={data.bairro || ''}
            onChange={(e) => handleChange('bairro', e.target.value)}
          />
        </Grid>
        <Grid size={3}>
          <TextField
            select
            fullWidth
            label="Estado"
            value={data.estado || ''}
            onChange={(e) => handleChange('estado', e.target.value)}
          >
            <MenuItem value="">Selecione o estado</MenuItem>
            {ESTADOS.map((estado) => (
              <MenuItem key={estado} value={estado}>
                {estado}
              </MenuItem>
            ))}
          </TextField>
        </Grid>
        <Grid size={3}>
          <TextField
            fullWidth
            label="Cidade"
            value={data.cidade || ''}
            onChange={(e) => handleChange('cidade', e.target.value)}
            placeholder="Digite a cidade"
          />
        </Grid>
      </Grid>
    </Box>
  );
}