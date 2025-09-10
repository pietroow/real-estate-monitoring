import Box from '@mui/material/Box';
import Stack from '@mui/material/Stack';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';

export default function HeaderCadastroObra() {
  return (
    <Stack paddingBottom={3} display="flex" flexDirection="row" justifyContent="space-between">
      <Typography variant="h4" color="#636363" gutterBottom>
        Cadastro de Obras
      </Typography>
      <Box display="flex" gap={2}>
        <Button
          style={{
            color: '#ffff',
            backgroundColor: '#00e676',
            border: '#69f0ae 1px solid',
            borderRadius: '8px',
            alignItems: 'center',
          }}
          variant="outlined"
        >
          <span
            className="material-icons"
            style={{
              cursor: 'pointer',
              alignItems: 'end',
              fontSize: '16px',
              color: '#ffff',
            }}
          >
            add
          </span>
          Novo
        </Button>
        <Button
          style={{
            backgroundColor: '#81d4fa',
            borderRadius: '8px',
            alignItems: 'center',
          }}
          variant="outlined"
        >
          <span
            className="material-icons"
            style={{
              cursor: 'pointer',
              alignItems: 'end',
              fontSize: '16px',
              color: '#ffff',
            }}
          >
            print
          </span>
        </Button>
      </Box>
    </Stack>
  );
}
