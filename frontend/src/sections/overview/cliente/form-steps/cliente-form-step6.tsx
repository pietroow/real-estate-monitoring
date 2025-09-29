import type { IClienteItem } from 'src/types/cliente';

import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import Typography from '@mui/material/Typography';

type Props = {
  data: Partial<IClienteItem>;
  onChange: (data: Partial<IClienteItem>) => void;
  errors?: Record<string, boolean>;
};

export function ClienteFormStep6({ data, onChange }: Props) {
  const handleChange = (field: string, value: any) => {
    onChange({ [field]: value } as any);
  };

  return (
    <Box>
      <Typography variant="h6" sx={{ mb: 3, color: 'text.primary' }}>
        Observações
      </Typography>

      <TextField
        fullWidth
        multiline
        rows={6}
        label="Comentários"
        value={data.comentarios || ''}
        onChange={(e) => handleChange('comentarios', e.target.value)}
        placeholder="Adicione observações, anotações ou comentários sobre o cliente..."
        sx={{
          '& .MuiOutlinedInput-root': {
            borderRadius: 2,
          },
        }}
      />
    </Box>
  );
}