import { CONFIG } from 'src/global-config';

import { FuncionariosView } from 'src/sections/funcionarios/view/funcionarios-view';

// ----------------------------------------------------------------------

const metadata = { title: `Funcionários | Dashboard - ${CONFIG.appName}` };

export default function FuncionariosPage() {
    return (
        <>
            <title>{metadata.title}</title>
            <FuncionariosView />
        </>
    );
}
