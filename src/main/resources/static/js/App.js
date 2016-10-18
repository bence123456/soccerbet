      let Appbar = mui.react.Appbar,
          Button = mui.react.Button,
          Container = mui.react.Container,
          Tab = mui.react.Tab,
          Tabs = mui.react.Tabs;

      class App extends React.Component {

        render() {
            return (
              <Tabs justified={true}>
                <Tab value="pane-1" label="Match Results">Pane-1</Tab>
                <Tab value="pane-2" label="Upcoming Matches">Pane-2</Tab>
                <Tab value="pane-3" label="Ranking">asd</Tab>
                <Tab value="pane-4" label="My bets">Pane-4</Tab>
              </Tabs>
            );
        }
      }

      ReactDOM.render(<App />, document.getElementById('app'));
