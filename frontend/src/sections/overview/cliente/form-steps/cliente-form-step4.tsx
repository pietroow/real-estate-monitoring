import type { IClienteItem } from 'src/types/cliente';

import { useState } from 'react';

import Box from '@mui/material/Box';
import Grid from '@mui/material/Grid';
import Paper from '@mui/material/Paper';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import Typography from '@mui/material/Typography';
import IconButton from '@mui/material/IconButton';

import { Iconify } from 'src/components/iconify';

type Props = {
  data: Partial<IClienteItem>;
  onChange: (data: Partial<IClienteItem>) => void;
  errors?: Record<string, boolean>;
};

export function ClienteFormStep4({ data, onChange, errors = {} }: Props) {
    const [showSecondContact, setShowSecondContact] = useState(false);

    const handleChange = (field: string, value: any) => {
      onChange({ [field]: value } as any);
    };

    return (
      <Box>
        <Typography variant="h6" sx={{ mb: 3, color: 'text.primary' }}>
          Contatos
        </Typography>

        <Grid container spacing={2}>
          <Grid size={12}>
            <Paper variant="outlined" sx={{ p: 1, bgcolor: 'grey.50' }}>
              <Box sx={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', mb: 2 }}>
                <Typography variant="subtitle1" sx={{ fontWeight: 'medium' }}>
                  Contato Principal
                </Typography>
              </Box>

              <Grid container spacing={2}>
              <Grid size={6}>
                <TextField
                  fullWidth
                  label="Nome *"
                  value={data.contato1Nome || ''}
                  onChange={(e) => handleChange('contato1Nome', e.target.value)}
                  required
                  error={errors.contato1Nome}
                  helperText={errors.contato1Nome ? 'Este campo é obrigatório' : ''}
                />
              </Grid>
              <Grid size={6}>
                <TextField
                  fullWidth
                  label="E-mail"
                  type="email"
                  value={data.contato1Email || ''}
                  onChange={(e) => handleChange('contato1Email', e.target.value)}
                />
              </Grid>

              <Grid size={6}>
                <TextField
                  fullWidth
                  label="Cargo"
                  value={data.contato1Cargo || ''}
                  onChange={(e) => handleChange('contato1Cargo', e.target.value)}
                />
              </Grid>
              <Grid size={6}>
                <TextField
                  fullWidth
                  label="Telefone"
                  value={data.contato1Telefone || ''}
                  onChange={(e) => handleChange('contato1Telefone', e.target.value)}
                  placeholder="(00) 00000-0000"
                />
              </Grid>

              <Grid size={6}>
                <TextField
                  fullWidth
                  label="Aniversário"
                  type="date"
                  value={data.contato1Aniversario || ''}
                  onChange={(e) => handleChange('contato1Aniversario', e.target.value)}
                  InputLabelProps={{
                    shrink: true,
                  }}
                />
              </Grid>
            </Grid>
            </Paper>
          </Grid>

          {showSecondContact && (
            <Grid size={12}>
              <Paper variant="outlined" sx={{ p: 1, bgcolor: 'grey.50' }}>
                <Box sx={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', mb: 2 }}>
                  <Typography variant="subtitle1" sx={{ fontWeight: 'medium' }}>
                    Contato Adicional
                  </Typography>
                  <IconButton
                    size="small"
                    onClick={() => setShowSecondContact(false)}
                    sx={{ color: 'error.main' }}
                  >
                    <Iconify icon="solar:close-circle-bold" width={20} />
                  </IconButton>
                </Box>

                <Grid container spacing={2}>
              <Grid size={6}>
                <TextField
                  fullWidth
                  label="Nome"
                  value={data.contato2Nome || ''}
                  onChange={(e) => handleChange('contato2Nome', e.target.value)}
                />
              </Grid>
              <Grid size={6}>
                <TextField
                  fullWidth
                  label="E-mail"
                  type="email"
                  value={data.contato2Email || ''}
                  onChange={(e) => handleChange('contato2Email', e.target.value)}
                />
              </Grid>

              <Grid size={6}>
                <TextField
                  fullWidth
                  label="Cargo"
                  value={data.contato2Cargo || ''}
                  onChange={(e) => handleChange('contato2Cargo', e.target.value)}
                />
              </Grid>
              <Grid size={6}>
                <TextField
                  fullWidth
                  label="Telefone"
                  value={data.contato2Telefone || ''}
                  onChange={(e) => handleChange('contato2Telefone', e.target.value)}
                  placeholder="(00) 00000-0000"
                />
              </Grid>

              <Grid size={6}>
                <TextField
                  fullWidth
                  label="Aniversário"
                  type="date"
                  value={data.contato2Aniversario || ''}
                  onChange={(e) => handleChange('contato2Aniversario', e.target.value)}
                  InputLabelProps={{
                    shrink: true,
                  }}
                />
              </Grid>
            </Grid>
              </Paper>
            </Grid>
          )}
          {!showSecondContact && (
            <Grid size={12}>
              <Button
                variant="outlined"
                startIcon={<Iconify icon="mingcute:add-line" />}
                onClick={() => setShowSecondContact(true)}
                sx={{
                  borderStyle: 'dashed',
                  borderColor: 'grey.300',
                  color: 'text.secondary',
                  py: 2,
                  width: '100%',
                  '&:hover': {
                    borderColor: 'primary.main',
                    bgcolor: 'grey.50',
                  }
                }}
              >
                Adicionar Contato
              </Button>
            </Grid>
          )}
        </Grid>
      </Box>
    );
  }