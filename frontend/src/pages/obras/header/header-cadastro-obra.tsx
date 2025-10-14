import Box from '@mui/material/Box';
import Stack from '@mui/material/Stack';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';

type HeaderCadastroObraProps = {
  onAddObraClick: () => void;
};

export default function HeaderCadastroObra({ onAddObraClick }: HeaderCadastroObraProps) {
  return (
    <Stack paddingBottom={3} display="flex" flexDirection="row" justifyContent="space-between">
      <Typography variant="h4" color="#636363" gutterBottom>
        Cadastro de Obras
      </Typography>
      <Box display="flex" gap={2}>
        <Button
          onClick={() => {
            onAddObraClick();
          }}
          color="success"
          variant="contained"
        >
          <span className="material-icons">add</span>
          Novo
        </Button>
      </Box>
    </Stack>
  );
}
