import { CONFIG } from 'src/global-config';

import { OverviewClienteView } from 'src/sections/overview/cliente/view/index';


const metadata = { title: `Lista de Clientes | Dashboard - ${CONFIG.appName}` };

export default function Page() {
  return (
    <>
      <title>{metadata.title}</title>
      <OverviewClienteView />
    </>
  );
}