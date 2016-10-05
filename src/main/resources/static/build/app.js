'use strict';



class App extends React.Component {

	constructor(props) {
		super(props);
		this.state = {teams: []};
	}

	componentDidMount() {

       fetch('http://localhost:8080/api/teams').then( (response) => {
                                                                return response.json() })
                                                                    .then( (json) => {
                                                                        this.setState({teams: json._embedded.teams});
                                                                    });
        //.then((response) => this.setState({teams: response.entity._embedded.teams}););

	}

	render() {
		return (
			<TeamList teams={this.state.teams}> </TeamList>
		)
	}
}

class TeamList extends React.Component{
	render() {
		var teams = this.props.teams.map(team =>
			<Team key={team._links.self.href} team={team}/>
		);
		return (
			<table>
				<tbody>
					<tr>
						<th>Name</th>
					</tr>
					{teams}
				</tbody>
			</table>
		)
	}
}

class Team extends React.Component{
	render() {
		return (
			<tr>
				<td>{this.props.team.name}</td>
			</tr>
		)
	}
}

ReactDOM.render(
	<App />,
	document.getElementById('react')
)