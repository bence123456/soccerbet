      let List = mui.react.List,
          ListItem = mui.react.ListItem,
          Divider = mui.react.Divider;

      class Szar extends React.Component {

        render() {
            return (
                  <List>
                    <ListItem primaryText="Inbox" />
                  </List>
                  <Divider />
                  <List>
                    <ListItem primaryText="All mail" />
                  </List>
            );
        }
      }

      ReactDOM.render(<Szar />, document.getElementById('szar'));