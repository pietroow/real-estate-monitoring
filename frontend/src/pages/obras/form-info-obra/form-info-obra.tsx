import Stack from '@mui/material/Stack';
import Switch from '@mui/material/Switch';
import TextField from '@mui/material/TextField';
import Typography from '@mui/material/Typography';
import Autocomplete from '@mui/material/Autocomplete';

const title: string = 'Informações da obra';

export default function FormInfoObra() {
  return (
    <Stack display="flex" gap={2}>
      <TextField
        size="small"
        required
        label="Nome da obra:"
        placeholder="Digite aqui a sua busca"
      />
      <Stack display="flex" direction="row" gap={2}>
        <Autocomplete
          size="small"
          disablePortal
          renderInput={(params) => <TextField {...params} label="Tipo" />}
          options={[]}
          fullWidth
        />
        <TextField
          size="small"
          label="Código da obra"
          fullWidth
          variant="outlined"
          placeholder="Código da obra"
        />
      </Stack>

      <Stack display="flex" direction="row" gap={2}>
        <Autocomplete
          size="small"
          disablePortal
          renderInput={(params) => <TextField {...params} label="Tipo de obra" />}
          options={[]}
          fullWidth
        />
        <TextField size="small" label="ART" fullWidth variant="outlined" placeholder="ART" />
      </Stack>

      <Stack display="flex" direction="row" gap={2}>
        <TextField
          size="small"
          label="Responsável técnico"
          fullWidth
          variant="outlined"
          placeholder="Responsável técnico"
        />
        <TextField
          size="small"
          label="Responsável da obra"
          fullWidth
          variant="outlined"
          placeholder="Responsável da obra"
        />
      </Stack>

      <Stack display="flex" direction="row" gap={2}>
        <TextField
          size="small"
          label="CEI / CNO"
          fullWidth
          variant="outlined"
          placeholder="CEI / CNO"
        />
        <TextField
          size="small"
          label="Área total"
          fullWidth
          variant="outlined"
          placeholder="Área total"
        />
        <TextField
          size="small"
          label="Unidade"
          fullWidth
          variant="outlined"
          placeholder="Unidade"
        />
      </Stack>

      <Stack display="flex" direction="row" gap={2}>
        <Autocomplete
          size="small"
          disablePortal
          renderInput={(params) => <TextField {...params} label="Quem paga" />}
          options={[]}
          fullWidth
        />
        <Autocomplete
          size="small"
          disablePortal
          renderInput={(params) => <TextField {...params} label="Conta" />}
          options={[]}
          fullWidth
        />
      </Stack>

      <TextField
        size="small"
        multiline
        rows={3}
        label="Comentário"
        fullWidth
        variant="outlined"
        placeholder="Comentário"
      />

      <Autocomplete
        size="small"
        disablePortal
        renderInput={(params) => <TextField {...params} label="Obra visível para" />}
        options={[]}
        fullWidth
      />

      <Stack display="flex" direction="row" gap={2}>
        <Stack justifyContent="start" display="flex">
          <Typography className="text-input" color="#636363" gutterBottom>
            Status da obra para lançamentos
          </Typography>
          <Switch />
        </Stack>

        <Stack justifyContent="start" display="flex">
          <Typography className="text-input" color="#636363" gutterBottom>
            Status da obra para faturamentos
          </Typography>
          <Switch />
        </Stack>

        <Stack justifyContent="start" display="flex">
          <Typography className="text-input" color="#636363" gutterBottom>
            Status da obra para compras
          </Typography>
          <Switch />
        </Stack>
      </Stack>
    </Stack>
  );
}
