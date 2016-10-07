      let Appbar = mui.react.Appbar,
          Button = mui.react.Button,
          Container = mui.react.Container;

      class MUIApp extends React.Component {
        render() {
          return (
            <div>
              <Appbar></Appbar>
              <Container>
                <Button color="primary">button</Button>
              </Container>
            </div>
          );
        }
      }

      ReactDOM.render(<MUIApp />, document.getElementById('muiapp'));



//import React from 'react';
//import ReactDOM from 'react-dom';
//import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
//import MyAwesomeReactComponent from './MyAwesomeReactComponent';
//
//const App = () => (
//  <MuiThemeProvider>
//    <MyAwesomeReactComponent />
//  </MuiThemeProvider>
//);
//
//ReactDOM.render(
//  <App />,
//  document.getElementById('muiapp')
//);
