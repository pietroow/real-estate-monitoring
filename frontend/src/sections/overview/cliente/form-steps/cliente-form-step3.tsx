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

const FONTES = [
  'Site',
  'Google Ads',
  'Facebook',
  'Instagram',
  'LinkedIn',
  'Indicação',
  'Telefone',
  'E-mail',
  'Outros'
];

const SETORES = [
  'Imobiliária',
  'Construção',
  'Corretagem',
  'Incorporação',
  'Arquitetura',
  'Engenharia',
  'Decoração',
  'Outros'
];

export function ClienteFormStep3({ data, onChange, errors = {} }: Props) {
  const handleChange = (field: string, value: any) => {
    onChange({ [field]: value } as any);
  };

  return (
    <Box>
      <Typography variant="h6" sx={{ mb: 3, color: 'text.primary' }}>
        Informações Complementares
      </Typography>

      <Grid container spacing={4}>
        <Grid size={6}>
          <TextField
            select
            fullWidth
            label="Fonte *"
            value={data.fonte || ''}
            onChange={(e) => handleChange('fonte', e.target.value)}
            size="medium"
            required
            error={errors.fonte}
            helperText={errors.fonte ? 'Este campo é obrigatório' : ''}
          >
            <MenuItem value="">Selecione a fonte</MenuItem>
            {FONTES.map((fonte) => (
              <MenuItem key={fonte} value={fonte}>
                {fonte}
              </MenuItem>
            ))}
          </TextField>
        </Grid>
        <Grid size={6}>
          <TextField
            select
            fullWidth
            label="Setor *"
            value={data.setor || ''}
            onChange={(e) => handleChange('setor', e.target.value)}
            size="medium"
            required
            error={errors.setor}
            helperText={errors.setor ? 'Este campo é obrigatório' : ''}
          >
            <MenuItem value="">Selecione o setor</MenuItem>
            {SETORES.map((setor) => (
              <MenuItem key={setor} value={setor}>
                {setor}
              </MenuItem>
            ))}
          </TextField>
        </Grid>

        <Grid size={12}>
          <TextField
            fullWidth
            label="Conta Bancária"
            value={data.contaBancaria || ''}
            onChange={(e) => handleChange('contaBancaria', e.target.value)}
            placeholder="Ex: Banco do Brasil - Agência: 1234-5 - Conta: 12345-6"
            multiline
            rows={3}
            sx={{
              '& .MuiOutlinedInput-root': {
                borderRadius: 1,
              },
            }}
          />
        </Grid>
      </Grid>
    </Box>
  );
}