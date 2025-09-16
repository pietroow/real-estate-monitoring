import Stack from '@mui/material/Stack';
import TextField from '@mui/material/TextField';
import Typography from '@mui/material/Typography';

export default function formContato() {
  return (
    <Stack display="flex" style={{ height: '100%' }} gap={6}>
      <Stack display="flex" gap={2}>
        <Typography variant="h5" color="#636363" gutterBottom>
          Contato 1
        </Typography>
        <Stack display="flex" direction="row" gap={2}>
          <TextField size="small" label="Nome" fullWidth variant="outlined" placeholder="Nome" />
          <TextField
            size="small"
            label="E-mail"
            fullWidth
            variant="outlined"
            placeholder="E-mail"
          />
        </Stack>

        <Stack display="flex" direction="row" gap={2}>
          <TextField size="small" label="Cargo" fullWidth variant="outlined" placeholder="Cargo" />
          <TextField
            size="small"
            label="Telefone"
            fullWidth
            variant="outlined"
            placeholder="Telefone"
          />
        </Stack>
      </Stack>

      <Stack display="flex" gap={2}>
        <Typography variant="h5" color="#636363" gutterBottom>
          Contato 2
        </Typography>
        <Stack display="flex" direction="row" gap={2}>
          <TextField size="small" label="Nome" fullWidth variant="outlined" placeholder="Nome" />
          <TextField
            size="small"
            label="E-mail"
            fullWidth
            variant="outlined"
            placeholder="E-mail"
          />
        </Stack>

        <Stack display="flex" direction="row" gap={2}>
          <TextField size="small" label="Cargo" fullWidth variant="outlined" placeholder="Cargo" />
          <TextField
            size="small"
            label="Telefone"
            fullWidth
            variant="outlined"
            placeholder="Telefone"
          />
        </Stack>
      </Stack>
    </Stack>
  );
}
