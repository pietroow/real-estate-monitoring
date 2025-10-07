import type { IClienteItem } from 'src/types/cliente';

import Box from '@mui/material/Box';
import Grid from '@mui/material/Grid';
import Radio from '@mui/material/Radio';
import Stack from '@mui/material/Stack';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import RadioGroup from '@mui/material/RadioGroup';
import Typography from '@mui/material/Typography';
import FormControl from '@mui/material/FormControl';
import FormControlLabel from '@mui/material/FormControlLabel';

type Props = {
  data: Partial<IClienteItem>;
  onChange: (data: Partial<IClienteItem>) => void;
  errors?: Record<string, boolean>;
};

export function ClienteFormStep1({ data, onChange, errors = {} }: Props) {
  const handleChange = (field: keyof IClienteItem, value: any) => {
    onChange({ [field]: value });
  };

  return (
    <Box>
      <Typography variant="h6" sx={{ mb: 3, color: 'text.primary' }}>
        Dados Principais
      </Typography>

      <Grid container spacing={3}>
        <Grid size={5}>
          <Typography variant="subtitle2" sx={{ mb: 2 }}>
            Tipo *
          </Typography>
          <FormControl>
            <RadioGroup
              row
              value={data.tipo || 'juridica'}
              onChange={(e) => handleChange('tipo', e.target.value)}
            >
              <FormControlLabel
                value="juridica"
                control={<Radio/>}
                label="Pessoa Jurídica"
              />
              <FormControlLabel
                value="fisica"
                control={<Radio/>}
                label="Pessoa Física"
              />
            </RadioGroup>
          </FormControl>
        </Grid>

        <Grid size={4}>
          <Typography variant="subtitle2" sx={{ mb: 2 }}>
            Também é fornecedor?
          </Typography>
          <FormControl>
            <RadioGroup
              row
              value={data.fornecedor || 'nao'}
              onChange={(e) => handleChange('fornecedor', e.target.value)}
            >
              <FormControlLabel
                value="sim"
                control={<Radio />}
                label="Sim"
              />
              <FormControlLabel
                value="nao"
                control={<Radio />}
                label="Não"
              />
            </RadioGroup>
          </FormControl>
        </Grid>

        <Grid size={3}>
          <Typography variant="subtitle2" sx={{ mb: 2 }}>
            Status
          </Typography>
          <Stack direction="row" spacing={1}>
            <Button
              variant={data.status === 'ativo' ? 'contained' : 'outlined'}
              color="info"
              size="medium"
              onClick={() => handleChange('status', 'ativo')}
              sx={{
                minWidth: 70,
                textTransform: 'none',
              }}
            >
              Ativo
            </Button>
            <Button
              variant={data.status === 'inativo' ? 'contained' : 'outlined'}
              color="info"
              size="medium"
              onClick={() => handleChange('status', 'inativo')}
              sx={{
                minWidth: 70,
                textTransform: 'none',
              }}
            >
              Inativo
            </Button>
          </Stack>
        </Grid>

        {data.tipo === 'juridica' ? (
          <>
            <Grid size={6}>
              <TextField
                fullWidth
                label="Nome Fantasia *"
                value={data.nome || ''}
                onChange={(e) => handleChange('nome', e.target.value)}
                required
                error={errors.nome}
                helperText={errors.nome ? 'Este campo é obrigatório' : ''}
              />
            </Grid>
            <Grid size={6}>
              <TextField
                fullWidth
                label="Razão Social"
                value={data.razaoSocial || ''}
                onChange={(e) => handleChange('razaoSocial', e.target.value)}
                required
                error={errors.nome}
              />
            </Grid>

            <Grid size={6}>
              <TextField
                fullWidth
                label="CNPJ"
                value={data.cnpj || ''}
                onChange={(e) => handleChange('cnpj', e.target.value)}
                placeholder="00.000.000/0000-00"
                error={errors.cnpj}
                helperText={errors.cnpj ? 'Este campo é obrigatório' : ''}
              />
            </Grid>
            <Grid size={6}>
              <TextField
                fullWidth
                label="Insc. Estadual"
                value={data.inscricaoEstadual || ''}
                onChange={(e) => handleChange('inscricaoEstadual', e.target.value)}
              />
            </Grid>

            <Grid size={6}>
              <TextField
                fullWidth
                label="Insc. Municipal"
                value={data.inscricaoMunicipal || ''}
                onChange={(e) => handleChange('inscricaoMunicipal', e.target.value)}
              />
            </Grid>
          </>
        ) : (
          <>
            <Grid size={12}>
              <TextField
                fullWidth
                label="Nome Completo *"
                value={data.nome || ''}
                onChange={(e) => handleChange('nome', e.target.value)}
                required
                placeholder="Digite o nome completo"
                error={errors.nome}
                helperText={errors.nome ? 'Este campo é obrigatório' : ''}
              />
            </Grid>

            <Grid size={6}>
              <TextField
                fullWidth
                label="CPF"
                value={data.cpf || ''}
                onChange={(e) => handleChange('cpf', e.target.value)}
                placeholder="000.000.000-00"
              />
            </Grid>
            <Grid size={6}>
              <TextField
                fullWidth
                label="RG"
                value={data.rg || ''}
                onChange={(e) => handleChange('rg', e.target.value)}
                placeholder="00.000.000-0"
              />
            </Grid>
          </>
        )}

        <Grid size={6}>
          <TextField
            fullWidth
            label="Telefone 1 *"
            value={data.telefone || ''}
            onChange={(e) => handleChange('telefone', e.target.value)}
            placeholder="(00) 00000-0000"
            required
            error={errors.telefone}
            helperText={errors.telefone ? 'Este campo é obrigatório' : ''}
          />
        </Grid>
        <Grid size={6}>
          <TextField
            fullWidth
            label="Telefone 2"
            value={data.telefone2 || ''}
            onChange={(e) => handleChange('telefone2', e.target.value)}
            placeholder="(00) 00000-0000"
          />
        </Grid>

        <Grid size={6}>
          <TextField
            fullWidth
            label="E-mail *"
            type="email"
            value={data.email || ''}
            onChange={(e) => handleChange('email', e.target.value)}
            placeholder="exemplo@empresa.com"
            required
            error={errors.email}
            helperText={errors.email ? 'Este campo é obrigatório' : ''}
          />
        </Grid>
      </Grid>
    </Box>
  );
}