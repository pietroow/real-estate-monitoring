import './form-obra.css';

import type { PassStepper } from 'src/models/pass-stepper';

import { useState } from 'react';

import Step from '@mui/material/Step';
import Modal from '@mui/material/Modal';
import Stack from '@mui/material/Stack';
import Button from '@mui/material/Button';
import Stepper from '@mui/material/Stepper';
import StepLabel from '@mui/material/StepLabel';
import Typography from '@mui/material/Typography';

import formCliente from '../form-cliente/form-cliente';
import formContato from '../form-contato/form-contato';
import formEndereco from '../form-endereco/form-endereco';
import FormInfoObra from '../form-info-obra/form-info-obra';

type FormObraProps = {
  open: boolean;
  close: () => void;
};

const steps: PassStepper[] = [
  { id: 1, title: 'Informações da obra', component: FormInfoObra },
  { id: 2, title: 'Endereço', component: formEndereco },
  { id: 3, title: 'Dados do cliente', component: formCliente },
  { id: 4, title: 'Contato', component: formContato },
];

export default function FormObra({ open, close }: FormObraProps) {
  const [checked, setChecked] = useState(true);
  const [currentStepp, setCurrentStepp] = useState(0);
  const [nextStepp, setNextStepp] = useState(0);

  const handleNextStepp = () => {
    const maxStepp = steps.length - 1;
    if (currentStepp < maxStepp) {
      setCurrentStepp((next) => next + 1);
    }
  };

  const handlePreviousStepp = () => {
    if (currentStepp > 0) {
      setCurrentStepp((prev) => prev - 1);
    }
  };

  const renderForm = () => {
    const SteppComponent = steps[currentStepp]?.component;
    return SteppComponent ? <SteppComponent /> : null;
  };

  return (
    <Modal open={open} onClose={close}>
      <Stack display="flex" className="form-obra-modal">
        <Stack display="flex" direction="row" justifyContent="space-between">
          <Typography variant="h3" color="#636363" gutterBottom>
            Formulário de Cadastro de Obra
          </Typography>

          <Stack display="flex" direction="row" gap={2} alignItems="center">
            <Button color="success" variant="contained">
              <span className="material-icons">check</span>
            </Button>

            <Button onClick={close} color="error" variant="contained">
              <span className="material-icons">close</span>
            </Button>
          </Stack>
        </Stack>

        <Stack className="pt-2 pb-2">
          <Stepper activeStep={currentStepp} alternativeLabel>
            {steps.map((step, index) => (
              <Step className="stepp-clicked" key={step.id}>
                <StepLabel onClick={() => setCurrentStepp(index)}>{step.title}</StepLabel>
              </Step>
            ))}
          </Stepper>
        </Stack>

        {renderForm()}

        <Stack display="flex" direction="row" justifyContent="space-between" alignItems="end">
          <Button variant="outlined" onClick={() => handlePreviousStepp()}>
            Anterior
          </Button>
          <Button variant="outlined" onClick={() => handleNextStepp()}>
            Proximo
          </Button>
        </Stack>
      </Stack>
    </Modal>
  );
}
