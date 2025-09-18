import { CONFIG } from 'src/global-config';

import { OverviewFuncionariosView } from 'src/sections/overview/funcionarios/view/overview-funcionarios-view';

// ----------------------------------------------------------------------

const metadata = { title: `Funcionários Overview | Dashboard - ${CONFIG.appName}` };

export default function FuncionariosOverviewPage() {
    return (
        <>
            <title>{metadata.title}</title>
            <OverviewFuncionariosView />
        </>
    );
}
